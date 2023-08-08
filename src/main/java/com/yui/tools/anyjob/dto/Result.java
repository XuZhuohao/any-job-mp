package com.yui.tools.anyjob.dto;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {


    private boolean success;

    private T data;

    private String message;

    private int errCode;

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> creatEmpty() {
        return new Result<>();
    }

    public static <T> Result<T> fail(String message, int errCode) {
        Result<T> objectResult = Result.creatEmpty();
        objectResult.setMessage(message);
        objectResult.setErrCode(errCode);
        return objectResult;
    }

    public static <T> Result<T> success(T data) {
        Result<T> objectResult = Result.creatEmpty();
        objectResult.setSuccess(true);
        objectResult.setData(data);
        return objectResult;
    }

}
