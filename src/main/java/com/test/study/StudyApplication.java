package com.test.study;

import com.test.study.guides.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class StudyApplication {


	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}



}
