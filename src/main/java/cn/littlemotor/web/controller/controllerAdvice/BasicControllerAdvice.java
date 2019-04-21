package cn.littlemotor.web.controller.controllerAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理一些控制器通用的方法比如exception处理以及session处理等
 * @author littlemotor
 * @data 19.4.10
 */
@ControllerAdvice(basePackages = {"cn.littlemotor.web.controller"},
        annotations = {Controller.class, RestController.class})
public class BasicControllerAdvice {

    /**
     * 统一处理使用过程中已知会遇到的一些exception,通过在返回的head里面加入错误信息，让前端获得错误提示
     * @param e
     * @return
     */
    @ExceptionHandler(value = {SQLException.class})
    //处理注册错误过程中出现的错误
    public ResponseEntity<String> registerException(Exception e){
        HttpHeaders httpHeaders = new HttpHeaders();
        //防止返回敏感信息，需要对错误名进行截取
        httpHeaders.add("Message",e.getClass().getSimpleName());
        return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 用于对每个请求添加必要对cookie，具体方法详见UserCookie
     * @param httpServletRequest
     * @param httpServletResponse
     */
    @ModelAttribute
    public void setCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //System.out.println("intercept cookie");
        UserCookie userCookie = new UserCookie(httpServletRequest);
        List<Cookie> cookieList = userCookie.getCookieList();
        for (Cookie cookie : cookieList){
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }
    }

}
