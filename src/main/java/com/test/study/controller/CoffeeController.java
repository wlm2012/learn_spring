package com.test.study.controller;

import com.test.study.entity.Coffee;
import com.test.study.primaryMapper.CoffeeRepository;
import com.test.study.util.test.CoffeExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.*;


@RestController
public class CoffeeController {


	private CoffeeRepository coffeeRepository;

	@Autowired
	public void setCoffeeRepository(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
	}

	@RequestMapping("/coffeeConcurrentTest")
	public void coffeeConcurrentTest() throws InterruptedException {

		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(20);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 20, TimeUnit.SECONDS, workQueue);
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, workQueue);
		for (int i = 0; i < 1000; i++) {
			if (workQueue.size() < 20) {
				CoffeExecutor coffeExecutor = new CoffeExecutor(10, coffeeRepository);
				executor.execute(coffeExecutor);
			} else {
				Thread.sleep(100);
				i--;
			}

		}
	}


	@RequestMapping("/coffeeTest")
	@Transactional
	public void coffeeTest() {
		double random = Math.random();
		BigDecimal bigDecimal = BigDecimal.valueOf(random);
		Coffee coffee = Coffee.builder().price(bigDecimal).build();
		coffeeRepository.save(coffee);


		coffee=Coffee.builder().price(BigDecimal.valueOf(0.22)).build();
		coffeeRepository.save(coffee);

/*		TPersInfo tPersInfo = new TPersInfo();
		tPersInfo.setGrzh("120");
		tPersInfo.setXingming("wlm");

		persInfoRepository.save(tPersInfo);*/

	}
}
