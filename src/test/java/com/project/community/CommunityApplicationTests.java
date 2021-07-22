package com.project.community;

import com.project.community.controller.AlphaController;
import com.project.community.dao.AlphaDao;
import com.project.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //保证测试类的配置类和正式环境是一样的

//Ioc 是Spring 容器，容器是自动被创建的，如何得到这个容器呢？
// 想要获得容器的类   实现ApplicationContextAware接口，并且实现set方法 ，
// ApplicationContext、继承于HierarchicalBeanFactory继承于BeanFactory---Spring容器的顶层接口，
// 子接口比父接口扩展了更多的方法
class CommunityApplicationTests implements ApplicationContextAware {

	private  ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


//———————————————————————————————————————————主动获取的方式————————————————————————————————————————
	@Test
	void testApplicationContext(){
		System.out.println(applicationContext);

		// 使用这个容器管理 bean
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		//强制获取
		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());

	}

	@Test
	//测试ApplicationContext  对于对象的初始化和销毁
	void testBeanManage(){
		AlphaService service = applicationContext.getBean(AlphaService.class);
		System.out.println(service);

		service = applicationContext.getBean(AlphaService.class);
		System.out.println(service);

	}

	@Test
	void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

//———————————————————————————————————————————依赖注入的方式————————————————————————————————————————

	//依赖注解可以通过  构造器，set方法注入，但是通常加在属性之前，直接注入给属性
	@Autowired
	private AlphaService alphaService;
	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Autowired
	private AlphaController alphaController;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(simpleDateFormat.format(new Date()));
		System.out.println(alphaService);

	}

}
