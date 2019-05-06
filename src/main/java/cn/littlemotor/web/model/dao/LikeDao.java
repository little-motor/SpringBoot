package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.service.content.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用于在数据库中存储用户点赞数
 */
@Repository
@Mapper
public interface LikeDao {

    /**
     * web.controller.LikeController
     * @param like
     */
    public void insertLikeNum(Like like);
}
