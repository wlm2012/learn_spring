package com.test.study.config;

import com.test.study.bean.UserFactory;
import com.test.study.entity.User;

import org.springframework.context.annotation.Bean;

// @Configuration
// @Import(User.class)

/**
 * 1）@Component...
 * 2) @Bean 
 * 3) @Import
 *    implements ImportSelector
 *    implements ImportBeanDefinitionRegistrar
 */


public class TestConfig{
    

    @Bean
    // @Scope("prototype")
    // @Lazy
    // @Conditional
    public User user() {
        User user=new User();
        user.setAge(11);
        user.setName("qqq");
        return user;
    }

    @Bean(initMethod = "init01" ,destroyMethod = "destroy01")
    public User user01() {
        return new User(11L,"ww",22,"qq.com");
    }

    @Bean
    public UserFactory userFactory() {
        return new UserFactory();
    }
}