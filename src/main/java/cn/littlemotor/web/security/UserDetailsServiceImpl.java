package cn.littlemotor.web.security;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import cn.littlemotor.web.model.service.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 自定义用户认证方式
 * @author littlemotor
 * @date 19.4.7
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao = null;

    private UserLogin userLogin = null;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        this.userLogin = userDao.getUserbyEmail(email);
        System.out.println(userLogin.toMap());
        return UserToUserdetails(userLogin);
    }

    //将user信息转换为userDetails对象
    private UserDetails UserToUserdetails(UserLogin userLogin){

        UserDetails userDetails =
                new CustomUser(userLogin);
        return  userDetails;
    }

    public User getUser() {
        return userLogin;
    }

    /**
     * 由于原来的User类记录的信息不是很全面，扩展后加入了UserLogin实例，便于查看登陆用户信息
     * CuntomUser实例在登陆验证后存在session内，key为"SPRING_SECURITY_CONTEXT"，
     * 返回类型为SecurityContextImpl,通过getAuthentication得到UsernamePasswordAuthenticationToken实例
     * 内部的princilp即为CustomUser
     */
    public class CustomUser extends org.springframework.security.core.userdetails.User{

        private static final long serialVersionUID = -7958264758047805588L;

        private UserLogin userLogin = null;

        public CustomUser(UserLogin userLogin) {
            super(userLogin.getEmail(),       //用户邮箱
                    userLogin.getPassword(),    //密码
                    userLogin.getActive() > 0,//账户是否有效
                    true,       //accountNonExpired,
                    true,     //credentialsNonExpired,
                    true,        //accountNonLocked,
                    //collection<? extends GrantedAuthority> authorities)此处使用了collections方法，因为直接用list add方法返回的是boolean
                    Collections.nCopies(1,new SimpleGrantedAuthority(userLogin.getRole())));
            this.userLogin = userLogin;
        }


        public UserLogin getUserLogin() {
            return userLogin;
        }

        public void setUserLogin(UserLogin userLogin) {
            this.userLogin = userLogin;
        }
    }
}
