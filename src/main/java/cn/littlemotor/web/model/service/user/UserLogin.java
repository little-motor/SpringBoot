package cn.littlemotor.web.model.service.user;

import cn.littlemotor.web.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用于用户登陆检查
 * @author littlemotor
 * @date 19.3.19
 */
public class UserLogin extends User{

    @Autowired
    UserDao userDao = null;

    //无参构造器防止controller注入对象时出错
    public UserLogin() {

    }

    public UserLogin(String email, String password, String rememberMe){
//        super.email = email;
//        this.password = password;
//        if(rememberMe == "true"){
//            this.rememberMe = true;
//        }
        System.out.println("调用了youcan");
    }

    public void validateUser(){
        //User user = userDao.getUserbyEmail(this.email);
        //System.out.println(user.getPassword());
    }

//    public static void main(String[] args) throws Exception{
//        UserLogin userLogin = new UserLogin("3432","fewf","true");
//        System.out.println(userLogin.rememberMe);
//        Method method = userLogin.getClass().getSuperclass().getMethod("getRememberMe", new Class[0]);
//
//        System.out.println(method.invoke(userLogin, new Object[]{}));
//        System.out.println(userLogin.getEmail());
//    }
}
