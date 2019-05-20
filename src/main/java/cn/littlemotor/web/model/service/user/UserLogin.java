package cn.littlemotor.web.model.service.user;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用于用户登陆检查
 * @author littlemotor
 * @date 19.3.19
 */
@Component
public class UserLogin extends User implements Serializable{

    private static final long serialVersionUID = 5450210140153577434L;

    //无参构造器防止controller注入对象时出错
    public UserLogin() {
    }


    //因为从数据库中取出来之后再setPassword的时候是不需要加密的
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

//        public static void main(String[] args) throws Exception{
//        UserLogin userLogin = new UserLogin();
//        userLogin.setrole
//        userLogin.setEmail("abc");
//        System.out.println(userLogin.getEmail());
//        System.out.println(userLogin.rememberMe);
//        Method method = userLogin.getClass().getSuperclass().getMethod("getRememberMe", new Class[0]);
//
//        System.out.println(method.invoke(userLogin, new Object[]{}));
//        System.out.println(userLogin.getEmail());
//    }
}
