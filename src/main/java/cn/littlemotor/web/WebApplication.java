package cn.littlemotor.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"cn.littlemotor.web.model","cn.littlemotor.web.controller"})
@MapperScan(
        //指定扫描包
        basePackages = "cn.littlemotor.web.model.dao.*",
        //指定sqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定标记的注解
        annotationClass = Repository.class
)
public class WebApplication {

//    @Autowired
//    SqlSessionFactory sqlSessionFactory = null;

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

//	@Bean
//    public MapperFactoryBean<UserDao> test(){
//        MapperFactoryBean<UserDao> mapperFactoryBean = new MapperFactoryBean<>();
//        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
//        mapperFactoryBean.setMapperInterface(UserDao.class);
//        return mapperFactoryBean;
//    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//	    //定义扫描器实例
//	    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//	    //加载SqlSessionFactory
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("cn.littlemotor.web.model.dao.*");
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        return mapperScannerConfigurer;
//    }
}

