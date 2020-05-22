package com.zhu.demo.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @Author: zhutao
 * @Date: 2020/5/22 21:56
 */
@RestControllerAdvice
public class CustomExtHandler extends BaseController {

    /**
     * 捕获全局异常，处理所有不可知的异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        logger.info("url {}, msg {}", request.getRequestURL(), e.getMessage());
        return Result.error(CodeMsg.INTER_ERROR, e.getMessage());
    }
}
