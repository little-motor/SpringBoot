package cn.littlemotor.controller;

import cn.littlemotor.model.MicroPostModel;
import cn.littlemotor.model.dao.MicroPostDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 返回主页
 * @author littlemotor
 *
 */
@Controller
public class HomeController {

    private MicroPostDao microPostDao;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        MicroPostModel microPostModel = new MicroPostModel(3l, "hello", "18.12.1");

        System.out.println("home bei diao yong");
        return "home";
  }

//    @RequestMapping(value = "/home",method = RequestMethod.GET)
//    public String homeRedirect() {
//        System.out.println("homeRedirct bei diao yong");
//        return "redirect:/";
//  }

}
