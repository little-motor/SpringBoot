package cn.littlemotor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登陆控制器，负责检验用户的登陆状态
 * @author littlemotor
 * @date 19.3.17
 */
@Controller
public class LoginController {
    @GetMapping(path = "/login")
    public String login(){

        return "login.html";
    }
}
