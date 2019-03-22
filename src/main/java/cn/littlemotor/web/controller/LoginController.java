package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import cn.littlemotor.web.model.service.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆控制器，负责检验用户的登陆状态
 * @author littlemotor
 * @date 19.3.17
 */
@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    Map<Integer, User> userInfo = new HashMap<>();

    //打开登陆页面
    @GetMapping(path = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    //用户登陆
    @PostMapping(path = "/login")
    public boolean login(@RequestBody UserLogin userLogin, HttpSession httpSession){
        User user = userDao.getUserbyEmail(userLogin.getEmail());
        boolean logined = userLogin.validateUser(user);
        //如果登陆成功将状态存到session
        if(logined){
            //每个人的会话不同，查到的user也不同
            httpSession.setAttribute("user", user);
        }
        return logined;
    }
}
