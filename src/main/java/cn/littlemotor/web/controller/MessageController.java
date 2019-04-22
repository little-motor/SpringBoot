package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.MessageDao;
import cn.littlemotor.web.model.service.content.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用于处理用户发送消息的控制器
 * @author littlemotor
 * @date 19.4.13
 */

@RestController
public class MessageController {

    @Autowired
    MessageDao messageDao = null;

    //objectMapper是线程安全的单例，需要提前配置好
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回message页面
     * @return
     */
    @GetMapping(path = "/message")
    public ModelAndView getMessage(){
        ModelAndView modelAndView = new ModelAndView("message.html");
        return modelAndView;
    }

    @GetMapping(path = "/message/getMessages")
    public ResponseEntity<String> getMessageData(@CookieValue(name = "userId") int userId){
        //这是缩进格式
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
        String body = null;
        try {
            List<Message> messageList = messageDao.getMessage(userId);
            body = objectMapper.writeValueAsString(messageList);
        } catch (JsonProcessingException jsonProcessingException){
            System.out.println("jsonProcessingException");
        }
        System.out.println(body);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.OK);
    }


    /**
     * 此处message的属性有messageId、用户id、userId、message、
     * likeNum、评论列表comments
     * @param message
     * @return
     */
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
