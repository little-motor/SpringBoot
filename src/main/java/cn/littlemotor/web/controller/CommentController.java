package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.CommentDao;
import cn.littlemotor.web.model.service.content.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用于处理用户的评论
 * @author littlemotor
 * @date 19.5.6
 */
@Controller
public class CommentController {

    @Autowired
    CommentDao commentDao = null;

    //插入用户发表的评论
    @PostMapping(path = "/message/comment")
    public void insertComment(@RequestBody Comment comment){
        commentDao.insertComment(comment);
    }

    @GetMapping(path = "/message/comment")
    public void getComments(){

    }



}
