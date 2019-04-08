package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import cn.littlemotor.web.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    UserDao userDao = null;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl = null;

    Map<Integer, User> userInfo = new HashMap<>();

    //打开登陆页面
    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //获取X-CSRF-TOKEN
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute("org.springframework.security.web.csrf.CsrfToken");
        httpServletResponse.addHeader("X-CSRF-TOKEN", csrfToken.getToken());
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    //用户登陆
    @PostMapping(path = "/login")
    public void login(HttpSession httpSession){
        User user = userDetailsServiceImpl.getUser();
        httpSession.setAttribute("user", user);
    }
}
