package com.test.study.primaryMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.study.entity.User;

import org.apache.ibatis.annotations.Mapper;


/**
 * @author wlm
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}