package cn.littlemotor.web.controller;


import cn.littlemotor.web.model.dao.UserDao;
import cn.littlemotor.web.model.service.user.User;
import cn.littlemotor.web.model.service.user.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * User控制器，负责显示用户信息和用户信息的修改
 * @author littlemotor
 * @date 19.1.14
 */
@RestController
public class UserController {

    @Autowired
    UserDao userDao = null;

    //objectMapper是线程安全的单例，需要提前配置好
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回user页面
     * @return
     */
    @GetMapping("/user/page")
    public ModelAndView getUserPage(){
        ModelAndView modelAndView = new ModelAndView("user.html");
        return modelAndView;
    }

    /**
     * 返回用户信息
     * @param id
     * @return
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public ResponseEntity<String> getUserInfo(@CookieValue(name = "userId") int id) {
        //json缩进格式
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
        String body = null;
        try {
            UserLogin user = userDao.getUserbyId(id);
            //注意JSON语法
            body = "{\"userName\":\"" + user.getName() +"\"}";

        } catch (Exception e){
        }
        ResponseEntity<String> responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PatchMapping(path = "/user/info")
    public HttpStatus patchUserInfo(@RequestBody User user){
        HttpStatus status = HttpStatus.OK;
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());
        System.out.println("password: " + user.getPassword());
        try {
            userDao.updateUser(user);
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return status;
    }


}
