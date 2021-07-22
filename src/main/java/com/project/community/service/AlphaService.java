package com.project.community.service;

import com.project.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")    //默认是单例@Scope("singleton"),初始化一个对象
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化/构造AlphaService");
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁AlphaService");
    }

    public String find (){
        return alphaDao.select();
    }
}
