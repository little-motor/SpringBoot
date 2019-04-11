package cn.littlemotor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆控制器，负责检验用户的登陆状态
 * @author littlemotor
 * @date 19.3.17
 */
@RestController
public class LoginController {

    //打开登陆页面
    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    //打开登陆页面
    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView("logout.html");
        return modelAndView;
    }

    //用户登陆由SpringSecurity的filter代处理
//    @PostMapping(path = "/login")
//    public ResponseEntity<String> login(HttpSession httpSession, HttpServletRequest httpServletRequest){
//        User user = userDetailsServiceImpl.getUser();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Message", "success");
//        return new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
//    }
}
