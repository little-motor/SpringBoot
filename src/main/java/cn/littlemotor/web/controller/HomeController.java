package cn.littlemotor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    //@Autowired
    //ProducerTest producerTest = null;

    @GetMapping(path = "/")
    public ModelAndView homeWithGet(){
        ModelAndView modelAndView = new ModelAndView("home.html");
        System.out.println("home");
        return modelAndView;
    }

    @GetMapping(path = "/home")
    public void homeRedirect(){
        System.out.println("home2");
       // producerTest.testProducer();
        //ModelAndView modelAndView = new ModelAndView("redirect:/");
    }


}
