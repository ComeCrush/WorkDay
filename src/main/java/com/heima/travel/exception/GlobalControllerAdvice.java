package com.heima.travel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义全局异常处理器
 * @author laofang
 * @description
 * @date 2021-06-06
 */
//@Component
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception ex, HttpServletResponse response) throws IOException {
        System.out.println(ex.getMessage());
        System.out.println(response);
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("/error.html");
    }
}
