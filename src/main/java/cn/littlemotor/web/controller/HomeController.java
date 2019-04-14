package cn.littlemotor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping(path = "/")
    public ModelAndView homeWithGet(){
        ModelAndView modelAndView = new ModelAndView("home.html");
        System.out.println("home");
        return modelAndView;
    }

    @GetMapping(path = "/home")
    public ModelAndView homeRedirect(){
        System.out.println("home2");
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

//    /**
//     * responseEntity可以封装对象，添加http头，以及响应码
//     */
//    @PostMapping(path = "/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        try {
//            userDao.setUser(user);
//            httpHeaders.add("registed", "success");
//        }catch (Exception e){
////            System.out.println("输出错误：" + e.toString());
//            throw e;
//
//        }
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
//    }

}
