package cn.littlemotor.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"cn.littlemotor.web.aspect","cn.littlemotor.web.controller","cn.littlemotor.web.test"})
public class WebApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}

