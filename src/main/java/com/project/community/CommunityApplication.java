package com.project.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		//自动创建Spring容器，目录中配置类和其子类的包会被扫描到
		//需要写注解才会被扫描  ，注解有4个
		//@Component    通用
		//@Controller   处理请求
		//@Service		业务
		//@Repository   数据库访问
		SpringApplication.run(CommunityApplication.class, args);
	}

}
