package cn.littlemotor.web.aspect.service.imp;

import cn.littlemotor.web.aspect.service.interf.UserService;
import cn.littlemotor.web.model.User;
import org.springframework.stereotype.Component;

/**
 * 用户接口实现类
 * @author littlemotor
 * @date 19.1.6
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        System.out.println("hi: " + user.getName());
    }
}
