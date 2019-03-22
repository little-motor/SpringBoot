package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.service.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class HomeController {

    //考虑有无session的情况，required改为false
    @GetMapping(path = "/")
    public ModelAndView homeWithSession(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("home.html");
        System.out.println("home");
        if(httpSession.getAttribute("user") != null){
            System.out.println(((User)httpSession.getAttribute("user")).getName());
        }
        return modelAndView;
    }

    @GetMapping(path = "/home")
    public String homeRedirect(){
        System.out.println("home2");
        return "redirect:/";
    }


}
