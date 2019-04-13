package cn.littlemotor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于处理用户发送消息的控制器
 * @author littlemotor
 * @date 19.4.13
 */

@RestController
public class MessageController {

    @GetMapping(value = "/message")
    public ModelAndView getMessage(){

        ModelAndView modelAndView = new ModelAndView("message.html");
        return modelAndView;
    }
}
