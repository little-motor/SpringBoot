package cn.littlemotor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册控制器
 * @author littlemotor
 * @date 19.1.26
 */
@Controller
public class RegisterController {

    @PostMapping(path = "/register")
    @ResponseBody
    public boolean register(@RequestBody String string){
        System.out.println(string);
//        Map<String, String> json = new HashMap<>();
//        json.put("post", string);
//        ModelAndView modelAndView = new ModelAndView("content", json);
        return true;
    }
}
