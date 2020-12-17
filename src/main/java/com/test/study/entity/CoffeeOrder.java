package com.test.study.entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_order")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {

	private String customer;

	@ManyToMany
	@JoinTable(name = "t_order_coffee")
	@OrderBy("id")
	private List<Coffee> items;

	@Enumerated
	@Column(nullable = false)
	private OrderState state;
}
