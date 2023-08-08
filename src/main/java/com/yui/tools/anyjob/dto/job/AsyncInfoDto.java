package com.yui.tools.anyjob.dto.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Future;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AsyncInfoDto {
    private long key;
    private Future<?> future;
}
