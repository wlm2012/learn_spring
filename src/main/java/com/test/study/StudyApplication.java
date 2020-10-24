package com.test.study;

import com.test.study.guides.storage.StorageProperties;
import com.test.study.guides.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wlm
 */
@SpringBootApplication
//@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class StudyApplication {


    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }


    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}
