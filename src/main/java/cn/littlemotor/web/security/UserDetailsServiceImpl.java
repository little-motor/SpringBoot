package cn.littlemotor.web.security;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserbyEmail(email);
        return UserToUserdetails(user);
    }

    //将user信息转换为userDetails对象
    private UserDetails UserToUserdetails(User user){
        String userName = user.getName();
        String password = user.getPassword();
        boolean enabled = (user.getActive() > 0);
        //账户是否过期
        boolean accountNonExpired = true;
        //证书是否过期
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorityList);
        return  userDetails;
    }
}
