package cn.littlemotor.web.model.service.user;

import org.springframework.stereotype.Component;

/**
 * 用于用户登陆检查
 * @author littlemotor
 * @date 19.3.19
 */
@Component
public class UserLogin extends User{


    //无参构造器防止controller注入对象时出错
    public UserLogin() {
    }

    //验证用户登陆时输入的账号和数据库中的信息是否匹配
    public boolean validateUser(User user){
        if(user == null){
            return false;
        }
        //注意此处比较字符串应该用equal方法而不是==
        //==方法比较两个对象的地址，肯定为false
        if(!user.getPassword().equals(this.getPassword())){
            return false;
        }
        return true;
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
