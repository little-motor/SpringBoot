package cn.littlemotor.web.controller;

import cn.littlemotor.web.aspect.service.interf.UserService;
import cn.littlemotor.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试aop切面编程
 * @author littlemotor
 * @date 19.1.5
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService = null;

//    @Autowired
//    private UserServiceOnlyClass userServiceOnlyClass = null;

//    @Autowired
//    @Qualifier(value = "userNoteImpl")
//    private UserNote userNote = null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(int id, String name, String note) {

        User user = new User(id,name,note);
        userService.printUser(user);
        //userServiceOnlyClass.printUser(user);
        return user;
    }

}
