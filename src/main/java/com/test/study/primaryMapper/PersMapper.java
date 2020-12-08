package com.test.study.primaryMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.study.entity.TPersInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PersMapper extends BaseMapper<TPersInfo> {
}
