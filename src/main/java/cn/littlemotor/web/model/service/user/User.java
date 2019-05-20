package cn.littlemotor.web.model.service.user;

import org.apache.ibatis.type.Alias;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private int id;
    private String name = null;
    private String sex = null;
    private String email = null;
    private String phone = null;
    protected String password = null;
    private int active = 0;
    private String activeToken = null;
    private boolean rememberMe = false;
    private String rememberToken = null;
    private boolean login = false;
    //默认为ROLE_USER
    private String role = "ROLE_USER";
    private Timestamp createDate = null;
    private String describe = null;

    private Map<String, String> userInfo = new HashMap<>();

    public User() {
    }

    public User(int id, String name, String sex, String email, String phone, String password, int active, String activeToken, boolean rememberMe, String rememberToken, boolean login, String role, Timestamp createDate, String describe) {
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
        this.role = role;
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

    //为了安全起见，password需要加密处理
    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
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

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        if(rememberMe == "true"){
            this.rememberMe = true;
        }
    }

    public void setRole(String roleContent){
        this.role = roleContent;
    }

    public String getRole(){
        return role;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
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
        userInfo.put("role", this.role);
        userInfo.put("active", Integer.toString(this.active));
        userInfo.put("remember", Boolean.toString(this.rememberMe));
        userInfo.put("login", Boolean.toString(this.login));
        userInfo.put("createDate", this.createDate.toString());
        return userInfo;
    }
}
