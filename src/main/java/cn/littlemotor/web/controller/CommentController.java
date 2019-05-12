package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.CommentDao;
import cn.littlemotor.web.model.service.content.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用于处理用户的评论
 * @author littlemotor
 * @date 19.5.6
 */
@RestController
public class CommentController {

    @Autowired
    CommentDao commentDao = null;

    //插入用户发表的评论
    @PostMapping(path = "/message/comment")
    public int insertComment(@RequestBody Comment comment){
        commentDao.insertComment(comment);
        return comment.getCommentId();
    }

    //删除评论
    @DeleteMapping(path = "/message/comment")
    public void deleteComment(int id){
        commentDao.deleteComment(id);
        System.out.println("delete id:" + id);
    }

    @GetMapping(path = "/message/comment")
    public void getComments(){

    }



}
