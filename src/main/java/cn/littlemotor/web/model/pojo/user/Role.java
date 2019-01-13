package cn.littlemotor.web.model.pojo.user;

import java.sql.Timestamp;

/**
 * 对应Role表
 * @author littlemotor
 * @date 19.1.13
 */
public class Role {

    private int id = 0;
    private String name = null;
    private int value = 0;
    private Timestamp createDate = null;
    private String describe = null;

    public Role() {
    }

    public Role(int id, String name, int value, Timestamp createDate, String describe) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createDate = createDate;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
