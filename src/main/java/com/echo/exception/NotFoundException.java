package com.echo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404
 * 自定义资源找不到时,异常处理类
 */
@ResponseStatus(HttpStatus.NOT_FOUND) //添加标识符,方便在handler中处理
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
