package com.test.study.util.work;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class ThreadPoolTest {


	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));


	@RequestMapping("/threadTest")
	public void threadTest(int num) {
		System.out.println(executor.getCorePoolSize());
		System.out.println(num);
		executor.setMaximumPoolSize(num);
		executor.setCorePoolSize(num);
		System.out.println(executor.getCorePoolSize());
	}

}
