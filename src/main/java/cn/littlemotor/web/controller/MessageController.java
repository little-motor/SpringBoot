package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.MessageDao;
import cn.littlemotor.web.model.service.content.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于处理用户发送消息的控制器
 * @author littlemotor
 * @date 19.4.13
 */

@RestController
public class MessageController {

    @Autowired
    MessageDao messageDao = null;

    @GetMapping(path = "/message")
    public ModelAndView getMessage(){
        ModelAndView modelAndView = new ModelAndView("message.html");
        return modelAndView;
    }

    @PostMapping(path = "/message")
    public ResponseEntity<String> postMessage(@RequestBody Message message){
        try{
            messageDao.insertMessage(message);
        } catch (Exception e){
            System.out.println(e);
            throw e;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Message","发送成功");
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
