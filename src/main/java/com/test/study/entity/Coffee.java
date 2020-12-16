package com.test.study.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "t_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class Coffee extends BaseEntity implements Serializable {

	private String name;

	private BigDecimal price;
}
