package cn.littlemotor.web.controller;


import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User控制器
 * @author littlemotor
 * @date 19.1.14
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao = null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(int id) {


        User user = userDao.getUser(id);
        return user;
    }

}
