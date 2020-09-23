package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class TestCompletionService {

	public static void testCompletionService() throws InterruptedException, ExecutionException {

		ExecutorService executor = new ThreadPoolExecutor(3, 20, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		Instant startTime = Instant.now();


		TestCallable testCallable = new TestCallable();
		testCallable.setTime(0);
		service.submit(testCallable);
		TestCallable testCallable1 = new TestCallable();
		testCallable1.setTime(3);
		service.submit(testCallable1);
		TestCallable testCallable2 = new TestCallable();
		testCallable2.setTime(5);
		service.submit(testCallable2);

		for (int i = 0; i < 3; i++) {
			try {
				Future<String> future = service.take();
				System.out.println(future.get());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		System.out.println("time= " + Duration.between(startTime, Instant.now()).toMillis());
	}
}
