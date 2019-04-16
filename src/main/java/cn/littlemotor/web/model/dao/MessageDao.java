package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.service.content.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 此处用于对用户发送对消息进行增删改查
 * @author littlemotor
 * @date 19.4.16
 */
//虽然限定了Repository接口，但是好像有没有都没区别
@Repository
//注意此处需要加Mapper注释，否则无法搜索到这个接口
@Mapper
public interface MessageDao {

    /**
     * web.controller.MessageController
     * 负责向数据库插入数据
     * @param message
     */
    public void insertMessage(Message message);


}
