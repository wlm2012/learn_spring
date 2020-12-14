package com.test.study.util.concurrency;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class ThreadLocalTest {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public ThreadLocal<String> threadName = ThreadLocal.withInitial(() -> null);

	public static void simpleDateFormatTest() {

		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				String date = dateFormat.format(new Date());
				System.out.println(date);
			}
		};

		for (int i = 0; i < 10000; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
		}

	}

	@RequestMapping("/threadName")
	public void threadLocalTest() {
		System.out.println("before:" + Thread.currentThread().getName() + ":" + threadName.get());
		threadName.set(Thread.currentThread().getName());
		System.out.println("after:" + Thread.currentThread().getName() + ":" + threadName.get());

	}
}
