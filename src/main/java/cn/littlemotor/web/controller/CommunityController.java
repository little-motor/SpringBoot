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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    MessageDao messageDao = null;

    //objectMapper是线程安全的单例，需要提前配置好
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获得社区主页
     * @return
     */
    @GetMapping(path = "/community")
    public ModelAndView getCommunityPage(){
        return new ModelAndView("community.html");
    }

    @GetMapping(path = "/community/message")
    public ResponseEntity<String> getCommunityMessage(){
        //json缩进格式
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
        String body = null;
        try {
            List<Message> messageList = messageDao.getAllMessage();
            body = objectMapper.writeValueAsString(messageList);
        } catch (JsonProcessingException jsonProcessingException){
            System.out.println("jsonProcessingException");
        }
        System.out.println(body);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.OK);
    }
}
