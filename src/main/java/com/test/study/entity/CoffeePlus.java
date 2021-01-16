package com.test.study.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("t_menu")
@Data
public class CoffeePlus {

	@TableId(type = IdType.AUTO)
	private Integer id;

	@NotEmpty
	private String name;

	@NotNull
	private BigDecimal price;


	private LocalDateTime creatTime;

	@Column(name = "updateTime")
	private LocalDateTime updateTime;
}
