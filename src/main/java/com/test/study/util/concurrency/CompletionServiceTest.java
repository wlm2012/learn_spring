package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class CompletionServiceTest {

	public static void testCompletionService() throws InterruptedException, ExecutionException {

		ExecutorService executor = new ThreadPoolExecutor(3, 20, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		Instant startTime = Instant.now();


		CallableTest testCallable = new CallableTest();
		testCallable.setTime(5);
		service.submit(testCallable);
		CallableTest testCallable1 = new CallableTest();
		testCallable1.setTime(3);
		service.submit(testCallable1);
		CallableTest testCallable2 = new CallableTest();
		testCallable2.setTime(0);
		service.submit(testCallable2);

		for (int i = 0; i < 3; i++) {
			try {
				Future<String> future = service.take();
				System.out.println(future.get());
			} catch (Exception e) {
				System.out.println("e.getMessage()=="+e.getMessage());
			}

		}

		System.out.println("time= " + Duration.between(startTime, Instant.now()).toMillis());
	}
}
