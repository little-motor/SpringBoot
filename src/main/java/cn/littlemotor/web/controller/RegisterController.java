package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册控制器
 * @author littlemotor
 * @date 19.1.26
 */
@Controller
public class RegisterController {

    @Autowired
    UserDao userDao = null;

    /**
     * 此处收到的json结构不是普通的结构无法直接实例化为user对象，需要自己定义httpmessageconverter
     * 在下次重构过程中进行修改
     */
    @ResponseBody
    @PostMapping(path = "/register")
    public boolean register(@RequestBody User user) {
        try {
            userDao.setUser(user);
        }catch (Exception e){
        }
        return true;
    }
}
