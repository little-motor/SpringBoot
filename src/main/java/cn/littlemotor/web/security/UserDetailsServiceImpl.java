package cn.littlemotor.web.security;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import cn.littlemotor.web.model.service.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义用户认证方式
 * @author littlemotor
 * @date 19.4.7w
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao = null;

    private UserLogin user = null;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        this.user = userDao.getUserbyEmail(email);
        System.out.println(user.toMap());
        return UserToUserdetails(user);
    }

    //将user信息转换为userDetails对象
    private UserDetails UserToUserdetails(UserLogin user){
        String userName = user.getEmail();
        String password = user.getPassword();
        boolean enabled = (user.getActive() > 0);
        //账户是否过期
        boolean accountNonExpired = true;
        //证书是否过期
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRoleContent()));

        UserDetails userDetails =
                new CustomUser(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorityList, user);
        return  userDetails;
    }

    public User getUser() {
        return user;
    }

    /**
     * 由于原来的User类记录的信息不是很全面，所以扩展之后加入了UserLogin实例，便于查看登陆用户信息
     * CuntomUser实例在登陆验证后存在session内，key为"SPRING_SECURITY_CONTEXT"，
     * 返回类型为SecurityContextImpl,通过getAuthentication得到UsernamePasswordAuthenticationToken实例
     * 内部的princilp即为CustomUser
     */
    public class CustomUser extends org.springframework.security.core.userdetails.User{

        private UserLogin userLogin = null;

        public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

        public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }

        public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, UserLogin userLogin) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
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
