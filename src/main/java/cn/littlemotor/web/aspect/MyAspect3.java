//package cn.littlemotor.web.aspect;
//
//import org.aspectj.lang.annotation.*;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 面向切面编程终端切面部分
// * @author littlemotor
// * @date 19.1.5
// */
//@Component
//@Aspect
//@Order(1)
//public class MyAspect3 {
//
//
////    //引入新的接口
////    @DeclareParents(value = "cn.littlemotor.web.aspect.service.interf.UserService+", defaultImpl = UserNoteImpl.class)
////    public UserNote userNote;
//
//    //定义切点
//    @Pointcut("execution(* cn.littlemotor.web.aspect.service.imp.UserServiceImpl.printUser(..))")
//    public void pointCut(){
//    }
//
//    @Before("pointCut()")
//    public void before(){
//        System.out.println("before3");
//    }
//
//    @After("pointCut()")
//    public void after(){
//        System.out.println("after3");
//    }
//
//    @AfterReturning("pointCut()")
//    public void afterReturning(){
//        System.out.println("afterReturning3");
//    }
//
//    @AfterThrowing("pointCut()")
//    public void afterThrowing(){
//        System.out.println("afterThrowing3");
//    }
//}
