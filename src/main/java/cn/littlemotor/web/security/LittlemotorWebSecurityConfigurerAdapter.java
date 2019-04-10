package cn.littlemotor.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 配置默认的Spring security安全机制，这里主要是使用自定义的UserDetailsService
 * @author littlemotor
 * @date 19.4.7
 */
@Service
public class LittlemotorWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "userDetailsServiceImpl")
    UserDetailsService userDetailsService = null;

    /**
     * 配置用户签名服务，主要是UserDetailsService机制，还可以给用户赋予角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //使用自定义UserDetailsService
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> daoAuthenticationConfigurer
                = auth.userDetailsService(userDetailsService);
        daoAuthenticationConfigurer.passwordEncoder(passwordEncoder);
    }

    /**
     * 用来配置filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 配置拦截保护的请求，比如什么请求放行，什么请求需要验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/home").authenticated()
                .and().authorizeRequests().antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login");
    }


}
