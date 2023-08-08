package com.yui.tools.anyjob.service.impl;

import com.yui.tools.anyjob.conf.CacheConfig;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AsyncAnyJobService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
@Service
public class AsyncAnyJobServiceImpl implements AsyncAnyJobService {
    @Autowired
    private CacheConfig cacheConfig;
    private static LruCache<Long, Object> CACHE = new LruCache<>(18, 0);

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 14, 1L,TimeUnit.HOURS, new LinkedBlockingQueue(100));

    @Override
    public <T> long process(InReceivingMessage inReceivingMessage, Function<InReceivingMessage, T> anyJobService) {
        updateCacheSize();
        long l = System.currentTimeMillis();
        threadPoolExecutor.submit(() -> {
            T apply = anyJobService.apply(inReceivingMessage);
            CACHE.put(l, apply);
        });
        return l;
    }

    @Override
    public Object getResult(long key) {
        return CACHE.get(key);
    }

    public void updateCacheSize() {
        CACHE.setMaxSize(cacheConfig.getMaxCache());
    }

    static class LruCache<K,V> extends LinkedHashMap<K, V> {
        @Getter
        @Setter
        private int maxSize;
        public LruCache(int initialCapacity, int maxSize) {
            super(initialCapacity);
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > getMaxSize();
        }
    }
}
