package com.test.study.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 1) @Bean initMethod  destroyMethod
 * 2) implements InitializingBean, DisposableBean
 * 3) @PostConstruct  @PreDestroy  加在方法上
 * 4) implements BeanPostProcessor 
 */



@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements InitializingBean, DisposableBean {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public void init01() {
        System.out.println("init .......");
    }

    public void destroy01() {
        System.out.println("destroy .........");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet .........");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy...........");

    }

}
