package cn.littlemotor.web.aspect.service.interf;

import cn.littlemotor.web.model.User;

/**
 * 用于切面中的接口，在imp包中被实现，然后可以被动态代理包装为面的一部分
 * @author littlemotor
 * @date 19.1.5
 */

public interface UserService {
    public void printUser(User user);
}
