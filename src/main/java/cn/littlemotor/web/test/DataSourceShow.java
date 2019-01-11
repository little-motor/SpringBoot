package cn.littlemotor.web.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceShow implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataSource datasource = applicationContext.getBean(DataSource.class);
        System.out.println("-----------数据源为：--------------");
        System.out.println(datasource.getClass().getName());
        System.out.println(datasource.getClass().getName());
        System.out.println("---------------------------------");
    }
}
