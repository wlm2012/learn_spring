package com.test.study.util.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@RestController
public class LockTest {

	@RequestMapping("/firstThread")
	public static synchronized void firstThread() {
		System.out.println("firstThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("firstThread");
	}

	@RequestMapping("/secondThread")
	public synchronized void secondThread() {
		System.out.println("secondThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("secondThread");
	}

	@RequestMapping("/thirdThread")
	public static synchronized void thirdThread() {
		System.out.println("thirdThread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thirdThread");
	}


	private static final Lock lock = new ReentrantLock();
	private static final Condition done = lock.newCondition();
	private static boolean isDone = false;

	public static void fourthThread() throws TimeoutException, InterruptedException {
		Instant startTime = Instant.now();
		Thread thread = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				res();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();

		get(7000_000_000L);
		System.out.println("time= " + Duration.between(startTime, Instant.now()).toMillis());
	}

	public static void get(long timeOut) throws InterruptedException, TimeoutException {
		long start = System.nanoTime();
		lock.lock();
		try {
			while (!isDone) {
				done.await(timeOut, TimeUnit.NANOSECONDS);
				long cur = System.nanoTime();
				if (isDone || cur - start > timeOut) {
					break;
				}
			}
		} finally {
			lock.unlock();
		}

		if (!isDone) {
			throw new TimeoutException();
		}
	}

	public static void res() {
		lock.lock();
		try {
			done.signalAll();
			isDone = true;
		} finally {
			lock.unlock();
		}
	}


}
