package cn.littlemotor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(path = "/")
    public String home(){
        System.out.println("home");
        return "forward:/home.html";
    }

    @GetMapping(path = "/home")
    public String home2(){
        System.out.println("home");
        return "redirect:/";
    }
}
