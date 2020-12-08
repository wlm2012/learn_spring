package com.test.study.secondMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.study.entity.TPersInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SecondPersMapper extends BaseMapper<TPersInfo> {
}
