package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.service.user.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆控制器，负责检验用户的登陆状态
 * @author littlemotor
 * @date 19.3.17
 */
@Controller
public class LoginController {

    //打开登陆页面
    @GetMapping(path = "/login")
    public String login(){

        return "login.html";
    }

    //用户登陆
    @ResponseBody
    @PostMapping(path = "/login")
    public boolean login(@RequestBody UserLogin userLogin){
        System.out.println("password: " + userLogin.getPassword());
        System.out.println("remember: " + userLogin.getRememberMe());
        System.out.println("email: " + userLogin.getEmail());
        return true;
    }
}
