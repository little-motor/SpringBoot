package cn.littlemotor.web.model.service.content;

import java.util.List;

/**
 * 用户发送的消息
 * @author littlemotor
 * @date 19.4.16
 */
public class Message {

    //message id
    private int messageId;
    //用户id
    private int userId;
    //用户发送信息
    private String messageData = null;
    //like的数量
    private int likeNum = 0;
    //评论列表
    private List<Comment> comments = null;

    public Message(){
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "messageid:" + getMessageId() + "userid: " + getUserId();
    }

}
