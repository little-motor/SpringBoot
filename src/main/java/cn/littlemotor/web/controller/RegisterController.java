package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册控制器
 * @author littlemotor
 * @date 19.1.26
 */
@RestController
public class RegisterController {

    @Autowired
    UserDao userDao = null;

    /**
     * responseEntity可以封装对象，添加http头，以及响应码
     */
    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userDao.setUser(user);
        }catch (Exception e){
//            System.out.println("输出错误：" + e.toString());
            throw e;

        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Message", "注册成功");
        return new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
    }

}
