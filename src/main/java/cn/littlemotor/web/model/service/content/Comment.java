package cn.littlemotor.web.model.service.content;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户评论POJO
 * @author littlemotor
 * @date 19.5.6
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 477750822408438451L;

    private int commentId;
    private int messageId;
    private String commentData = null;
    private int userId;
    private String userName = null;
    private Timestamp commentCreateDate = null;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCommentCreateDate() {
        return commentCreateDate;
    }

    public void setCommentCreateDate(Timestamp commentCreateDate) {
        this.commentCreateDate = commentCreateDate;
    }
}
