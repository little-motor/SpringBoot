package cn.littlemotor.web;

import cn.littlemotor.web.interceptor.DeCROSInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"cn.littlemotor.web.model", "cn.littlemotor.web.controller"})
//定义Mabatis的dao接口位置
@MapperScan(
        //指定扫描包
        basePackages = "cn.littlemotor.web.model.dao.*",
        //指定sqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定标记的注解
        annotationClass = Repository.class
)
public class WebApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册拦截器到Spring MVC机制，然后返回一个InterceptorRegistration
        //此处添加的是防止浏览器提示CROS的拦截器
        InterceptorRegistration deCROSInterceptorRegistration = registry.addInterceptor(new DeCROSInterceptor());
        //指定拦截器匹配模式
        deCROSInterceptorRegistration.addPathPatterns("/**");

    }
}

