package org.wzxy.breeze.myException;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 覃能健
 * @create 2020-04
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e){
        System.out.println("无权限哦~");
        e.printStackTrace();
        return  "无权限访问";
    }

}
