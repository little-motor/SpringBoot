package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.service.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    //考虑有无session的情况，required改为false
    @GetMapping(path = "/")
    public String homeWithSession(HttpSession httpSession){
        System.out.println("home");
        if(httpSession.getAttribute("user") != null){
            System.out.println(((User)httpSession.getAttribute("user")).getName());
        }
        return "/home.html";
    }

    @GetMapping(path = "/home")
    public String homeRedirect(){
        System.out.println("home2");
        return "redirect:/";
    }


}
