package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.service.content.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 负责向数据库插入用户发送的消息
     * @param message
     */
    public void insertMessage(Message message);

    /**
     * web.controller.MessageController
     * 负责在插入messge表后修改user_message中间表
     */
//    public void insertUserMessage(Message message);

    /**
     * web.controller.MessageController
     * 返回查询到的Message对象list
     * @return
     */
    public List<Message> getMessage(int userId);

}
