package com.project.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration  //注解这个是配置类，不是一个普通的类
public class AlphaConfig {

    //因为项目中时间格式基本都一样，所以装配到Bean中可以反复用,即方法返回的对象将会被装配到容器中
    //simpleDateFormat 方法名就是 Bean的名字
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
