package cn.littlemotor.web.model.service.content;

import java.util.List;

/**
 * 用户发送的消息
 * @author littlemotor
 * @date 19.4.16
 */
public class Message {

    //用户id
    private int userId;
    //用户发送信息
    private String message = null;
    //like的数量
    private int likeNum = 0;
    //评论列表
    private List<String> comments = null;


    public Message(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    /**
     * 用于处理User_Message中间表
     */
    public class User_Message{

        //用户id
        private int userId;
        //消息id
        private int messageId;

        public User_Message(){

        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }
    }
}
