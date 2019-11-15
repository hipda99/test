package com.zaumal.test.applicationContext.annotationConfigApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaumal.test.applicationContext.annotationConfigApplicationContext.conf.AppConfig;
import com.zaumal.test.applicationContext.annotationConfigApplicationContext.entity.People;

public class ACACTestMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//方法一
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//方法二
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//		ac.register(AppConfig.class);
//		ac.refresh();
		
		People people = ac.getBean(People.class);
		
		System.out.println(people.getName() + " : " + people.getAge());
	}
}
