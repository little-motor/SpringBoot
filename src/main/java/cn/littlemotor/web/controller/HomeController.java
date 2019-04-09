package cn.littlemotor.web.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    //考虑有无session的情况，required改为false
    @GetMapping(path = "/")
    public ModelAndView homeWithSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        /** 获取token的方式有多种可以通过request的getAttribute也可以通过session
        * 他们的可以非别为"org.springframework.security.web.csrf.CsrfToken"、"_csrf"和"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"
        * 推荐使用servlet获取，session不太稳定
        */
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute("org.springframework.security.web.csrf.CsrfToken");
        //System.out.println(csrfToken.getToken());
        Cookie csrfCookie = new Cookie("X-CSRF-TOKEN", csrfToken.getToken());
        httpServletResponse.addCookie(csrfCookie);
        ModelAndView modelAndView = new ModelAndView("home.html");
        System.out.println("home");
        return modelAndView;
    }

    @GetMapping(path = "/home")
    public ModelAndView homeRedirect(){
        System.out.println("home2");
        ModelAndView modelAndView = new ModelAndView("home.html");
        return modelAndView;
    }

//    /**
//     * responseEntity可以封装对象，添加http头，以及响应码
//     */
//    @PostMapping(path = "/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        try {
//            userDao.setUser(user);
//            httpHeaders.add("registed", "success");
//        }catch (Exception e){
////            System.out.println("输出错误：" + e.toString());
//            throw e;
//
//        }
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
//    }

}
