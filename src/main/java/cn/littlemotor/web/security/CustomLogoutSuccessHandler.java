package cn.littlemotor.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定制注销成功后的一些后续操作，主要是删除cookie
 *
 * @author littlemotor
 * @date 19.4.14
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    public CustomLogoutSuccessHandler(){

    }

    //负责退出后对所有cookie进行删除操作
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            cookie.setValue(null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

    }
}
