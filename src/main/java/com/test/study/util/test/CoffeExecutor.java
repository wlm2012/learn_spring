package com.test.study.util.test;

import com.test.study.entity.Coffee;
import com.test.study.primaryMapper.CoffeeRepository;

import java.math.BigDecimal;

public class CoffeExecutor implements Runnable {

	private int num;

	private CoffeeRepository repository;

	public CoffeExecutor(int num, CoffeeRepository repository) {
		this.num = num;
		this.repository = repository;
	}


	@Override
	public void run() {
		for (int i = 0; i < num; i++) {
			double random = Math.random();
			BigDecimal bigDecimal = BigDecimal.valueOf(random);
			Coffee coffee = Coffee.builder().price(bigDecimal).build();
			repository.save(coffee);
		}
	}
}
