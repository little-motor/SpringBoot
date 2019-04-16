package cn.littlemotor.web.controller.controllerAdvice;

import cn.littlemotor.web.model.service.user.UserLogin;
import cn.littlemotor.web.security.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 对一些常用cookie进行操作的通用方法，注意每次要先初始化之后再使用
 * 经过考虑我觉得此处不应该使用static方法，而应该用对象实例化，应为每个session对应着不同的用户访问
 * 如果用static方法，那么所有访问用户都共享一个cookie方法这是不合理的、阻塞的、不安全的、即使用多线程也不太好
 * 总而言之要么是时间换空间，要么是空间换时间，此处还是实例化比较妥当
 * @author littlemotor
 * @date 19.4.10
 */
public class UserCookie {

    private List<Cookie> cookieList = new ArrayList<>();

    //用户id
    private  Cookie idCookie = null;
    //用户名
    private Cookie userNameCookie = null;
    //用户邮箱
    private Cookie emailCookie = null;
    //登陆状态
    private Cookie loginCookie = null;
    //csrfToken
    private Cookie csrfTokenCookie = null;

    //用户请求
    private HttpServletRequest httpServletRequest = null;

    public UserCookie(){

    }

    public UserCookie(HttpServletRequest httpServletRequest){
        this.httpServletRequest = httpServletRequest;
    }

    public void init(){
        this.cookieList = null;
    }


    public List<Cookie> getCookieList(){
        /** 获取token的方式有多种可以通过request的getAttribute也可以通过session
         * 他们的可以非别为"org.springframework.security.web.csrf.CsrfToken"、"_csrf"和"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"
         * 推荐使用servlet获取，session不太稳定
         */
        CsrfToken csrfToken = (CsrfToken) this.httpServletRequest.getAttribute("org.springframework.security.web.csrf.CsrfToken");
        this.csrfTokenCookie = new Cookie("X-CSRF-TOKEN", csrfToken.getToken());
        cookieList.add(csrfTokenCookie);

        /**
         * 登陆后用户信息作为SecurityContextImpl实例由Spring Security存放在session中
         * 通过获取这个实例后再获取他的Authentication实例的principal主体得到用户基本的登陆信息
         */
        HttpSession httpSession = this.httpServletRequest.getSession();
        try {
            SecurityContextImpl securityContext = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication = securityContext.getAuthentication();
            User customUser = (UserDetailsServiceImpl.CustomUser) authentication.getPrincipal();
            UserLogin userLogin = ((UserDetailsServiceImpl.CustomUser) customUser).getUserLogin();
            if(userLogin != null){

                //idCookie
                this.idCookie = new Cookie("userId", Integer.toString(userLogin.getId()));
                cookieList.add(idCookie);

                //loginCookie判断登陆状态
                this.loginCookie = new Cookie("login", "true");
                cookieList.add(loginCookie);

                //userNameCookie
                this.userNameCookie = new Cookie("userName", userLogin.getName());
                cookieList.add(userNameCookie);

                //emailCookie
                this.userNameCookie = new Cookie("email", userLogin.getEmail());
                cookieList.add(userNameCookie);
            } else {

                //loginCookie判断登陆状态
                this.loginCookie = new Cookie("login", "false");
                cookieList.add(loginCookie);
            }
        } catch (Exception e){

            //loginCookie判断登陆状态
            this.loginCookie = new Cookie("login", "false");
            cookieList.add(loginCookie);
            //System.out.println("UserCookie has some wrong");
        } finally {
           return cookieList;
        }
    }

    public void setCookieList(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }

    public Cookie getLoginCookie() {
        return loginCookie;
    }

    public void setLoginCookie(Cookie loginCookie) {
        this.loginCookie = loginCookie;
    }

    public Cookie getCsrfTokenCookie() {
        return csrfTokenCookie;
    }

    public void setCsrfTokenCookie(Cookie csrfTokenCookie) {
        this.csrfTokenCookie = csrfTokenCookie;
    }

    public Cookie getUserNameCookie() {
        return userNameCookie;
    }

    public void setUserNameCookie(Cookie userNameCookie) {
        this.userNameCookie = userNameCookie;
    }
}
