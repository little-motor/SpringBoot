package cn.littlemotor.web.model.service.content;

import java.io.Serializable;

public class Like implements Serializable {

    private static final long serialVersionUID = 6929636706522395940L;

    private int likeId;
    private int messageId;
    private int likeNum;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
