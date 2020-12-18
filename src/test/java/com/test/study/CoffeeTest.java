package com.test.study;

import com.sleepycat.je.rep.elections.ElectionAgentThread;
import com.test.study.primaryMapper.CoffeeRepository;
import com.test.study.util.test.CoffeExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class CoffeeTest {

	private CoffeeRepository repository;

	@Autowired
	public void setRepository(CoffeeRepository repository) {
		this.repository = repository;
	}

	@Test
	public void coffeeTest() throws InterruptedException {

		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(20);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 20, TimeUnit.SECONDS, workQueue);
		for (int i = 0; i < 1000; i++) {
			if (workQueue.size() < 20) {
				CoffeExecutor coffeExecutor = new CoffeExecutor(10, repository);
				executor.execute(coffeExecutor);
			} else {
				Thread.sleep(100);
				i--;
			}

		}
	}
}
