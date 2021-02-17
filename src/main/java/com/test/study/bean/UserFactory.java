package com.test.study.bean;

import com.test.study.entity.User;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wlm
 */
public class UserFactory implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {

        return new User(33L, "linux", 55, "??");
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}