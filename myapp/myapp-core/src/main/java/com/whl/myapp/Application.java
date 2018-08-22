package com.whl.myapp;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 16/4/26.
 */
// Spring Boot 应用的标识
@SpringBootApplication
@MapperScan("com.whl.myapp.dao")
public class Application {
	 @PostConstruct
	   void setDefaultTimezone() {
	      TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	   }
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
