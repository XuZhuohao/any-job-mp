package com.yui.tools.anyjob.controller;

import com.yui.tools.anyjob.service.AsyncAnyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
@RestController
@RequestMapping("syncJob")
public class AsyncJobController {
    @Autowired
    private AsyncAnyJobService asyncAnyJobService;

    @GetMapping("result/{key}")
    public Object getResult(@PathVariable("key") long key) {
        return asyncAnyJobService.getResult(key);
    }
}
