package com.test.study;

import java.util.Map;

import com.test.study.config.TestConfig;
import com.test.study.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class StudyApplicationTests {

	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);


	@Test
	void contextLoads() {
	}

	@Test
	public void beanTest() {

		//only single bean 
		// User bean= applicationContext.getBean(User.class);

		String[] beanName=applicationContext.getBeanNamesForType(User.class);

		for (String name : beanName) {
			System.out.println(name);
		}

		Map<String,User> map= applicationContext.getBeansOfType(User.class);
		System.out.println(map);
		
		applicationContext.close();
	}

}
