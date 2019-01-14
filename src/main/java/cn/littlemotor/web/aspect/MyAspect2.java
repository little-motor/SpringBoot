//package cn.littlemotor.web.aspect;
//
//import org.aspectj.lang.annotation.*;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//@Order(2)
//public class MyAspect2 {
//    //定义切点
//    @Pointcut("execution(* cn.littlemotor.web.aspect.service.imp.UserServiceImpl.printUser(..))")
//    public void pointCut(){
//    }
//
//    @Before("pointCut()")
//    public void before(){
//        System.out.println("before2");
//    }
//
//    @After("pointCut()")
//    public void after(){
//        System.out.println("after2");
//    }
//
//    @AfterReturning("pointCut()")
//    public void afterReturning(){
//        System.out.println("afterReturning2");
//    }
//
//    @AfterThrowing("pointCut()")
//    public void afterThrowing(){
//        System.out.println("afterThrowing2");
//    }
//}
