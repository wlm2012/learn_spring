package com.test.study.util.concurrency;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierTest {

	public static void cyclicBarrierTest(int param) throws InterruptedException {


		//First if queue is full and there is task,ThreadPoolExecutor will create new thread and the newest task will execute
		ExecutorService executorService =
				new ThreadPoolExecutor(5, 20, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		Instant startTime = Instant.now();

		for (int i = 0; i < param; i++) {
			Future<Integer> future1 = executorService.submit(() -> {
				int result = 5;
				System.out.println("time1-0= " + Duration.between(startTime, Instant.now()).toSeconds());
				TimeUnit.SECONDS.sleep(result);
				System.out.println("time1-1= " + Duration.between(startTime, Instant.now()).toSeconds());
				cyclicBarrier.await();
				System.out.println("time1-2= " + Duration.between(startTime, Instant.now()).toSeconds());
				return result;
			});

			Future<Integer> future2 = executorService.submit(() -> {
				int result = 3;
				System.out.println("time2-0= " + Duration.between(startTime, Instant.now()).toSeconds());
				TimeUnit.SECONDS.sleep(result);
				System.out.println("time2-1= " + Duration.between(startTime, Instant.now()).toSeconds());
				cyclicBarrier.await();
				System.out.println("time2-2= " + Duration.between(startTime, Instant.now()).toSeconds());
				return result;
			});
		}

		TimeUnit.SECONDS.sleep(param * 5);

	}
}
