package cn.littlemotor.web.controller.controllerAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 统一处理使用过程中已知会遇到的一些exception
 */
@ControllerAdvice(basePackages = {"cn.littlemotor.web.controller"},
        annotations = {Controller.class, RestController.class})
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {SQLException.class})
    //处理注册错误过程中出现的错误
    public ResponseEntity<String> registerException(Exception e){
        HttpHeaders httpHeaders = new HttpHeaders();
        //防止返回敏感信息，需要对错误名进行截取
        httpHeaders.add("wrongMessage",e.getClass().getSimpleName());
        return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
