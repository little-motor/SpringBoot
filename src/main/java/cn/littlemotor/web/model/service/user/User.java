package cn.littlemotor.web.model.service.user;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 对应User表
 * @author littlemotor
 * @date 19.1.13
 */
@Alias(value = "User")
public class User {

    private int id;
    private String name = null;
    private String sex = null;
    private String email = null;
    private String phone = null;
    private String password = null;
    private int active = 0;
    private String activeToken = null;
    private int rememberMe = 0;
    private String rememberToken = null;
    private int login = 0;
    //默认普通用户
    private int roleId = 1;
    private Timestamp createDate = null;
    private String describe = null;

    private Map<String, String> userInfo = new HashMap<>();

    public User() {
    }

    public User(int id, String name, String sex, String email, String phone, String password, int active, String activeToken, int rememberMe, String rememberToken, int login, int roleId, Timestamp createDate, String describe) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.active = active;
        this.activeToken = activeToken;
        this.rememberMe = rememberMe;
        this.rememberToken = rememberToken;
        this.login = login;
        this.roleId = roleId;
        this.createDate = createDate;
        this.describe = describe;
    }

    //用于主页注册
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getActiveToken() {
        return activeToken;
    }

    public void setActiveToken(String activeToken) {
        this.activeToken = activeToken;
    }

    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public Map<String,String> toMap(){
        userInfo.put("id",Integer.toString(this.id));
        userInfo.put("name", this.name);
        userInfo.put("sex", this.sex);
        userInfo.put("email", this.email);
        userInfo.put("phone", this.phone);
        userInfo.put("password", this.password);
        userInfo.put("active", Integer.toString(this.active));
        userInfo.put("remember", Integer.toString(this.rememberMe));
        userInfo.put("login", Integer.toString(this.login));
        userInfo.put("roleId", Integer.toString(this.roleId));
        userInfo.put("createDate", this.createDate.toString());
        return userInfo;
    }
}
