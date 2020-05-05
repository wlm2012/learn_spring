package com.test.study;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.PageHelper;
import com.test.study.entity.User;
import com.test.study.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {
    
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}