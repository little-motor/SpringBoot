package cn.littlemotor.model;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 微博暂时包括消息的内容、时间戳
 * @author littlemotor
 * @date 18.12。6
 */
public class MicroPostModel implements Serializable {

    private static final long serialVersionUID = 5482177178299391801L;

    //小数部分位数和整数部分位数
    @Digits(fraction = 0, integer = 5)
    private Long id;


    @NotNull
    @Size(max = 200)
    private String message;

    @NotNull
    private String createdAt;

    public MicroPostModel() {
    }

    public MicroPostModel(Long id, String message, String createdAt) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
