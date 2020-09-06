package com.test.study.util.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LockTest {

	@RequestMapping("/firstThread")
	public static synchronized void firstThread(){
		System.out.println("firstThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("firstThread");
	}

	@RequestMapping("/secondThread")
	public synchronized void secondThread(){
		System.out.println("secondThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("secondThread");
	}

	@RequestMapping("/thirdThread")
	public static synchronized void thirdThread(){
		System.out.println("thirdThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thirdThread");
	}
}
