package com.test.study.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("t_order_plus")
@Data
@Builder
@AllArgsConstructor
public class CoffeePlus {

	@TableId(type = IdType.AUTO)
	private Integer id;

	@NotEmpty
	private String name;

	@NotNull
	private BigDecimal price;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;
}
