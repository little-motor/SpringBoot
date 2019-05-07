package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.service.content.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 负责数据库中对comment表的操作
 * @author littlemotor
 * @date 19.5.6
 */
@Repository
@Mapper
public interface CommentDao {

    /**
     * 插入用户评论
     * web.controller.CommentController
     * @param comment
     */
    public void insertComment(Comment comment);
}
