package com.overseas.army.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVO<?> handleException(Exception e) {
        e.printStackTrace();
        return ResultVO.error(500, "服务器内部错误: " + e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResultVO.error(400, e.getMessage());
    }
}