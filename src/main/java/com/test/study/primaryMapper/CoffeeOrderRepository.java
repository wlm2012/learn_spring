package com.test.study.primaryMapper;


import com.test.study.entity.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
	List<CoffeeOrder> findByCustomerOrderById(String customer);

	List<CoffeeOrder> findByItems_Name(String name);
}
