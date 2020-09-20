package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class CompletedFutureTest {

	public static void thenApplyExample() throws InterruptedException {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("aa").thenApply(
				String::toUpperCase
		);

		System.out.println("AA".equals(cf.getNow(null)));


		CompletableFuture<String> cf1 = CompletableFuture.completedFuture("bb")
				.thenApplyAsync(
						s -> {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							return s.toUpperCase();
						}
				);

		Thread.sleep(100);
		System.out.println("BB".equals(cf1.getNow(null)));
	}


	public static void completedFutureTest() throws ExecutionException, InterruptedException {
		Instant startTime = Instant.now();
		ExecutorService service =
				new ThreadPoolExecutor(5, 20, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));

		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "a" + "b";
		}, service).thenApply(String::toUpperCase);

		System.out.println(cf.join());
		System.out.println("time= " + Duration.between(startTime, Instant.now()).toMillis());
	}

	public static void exceptionally() {
		Instant startTime = Instant.now();
		ExecutorService service =
				new ThreadPoolExecutor(5, 20, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 7 / 0, service)
				.exceptionally(e -> {
					System.out.println("exceptionally" + e);
					return 0;
				})
				.handle((result, e) -> {
					System.out.println("handle" + e);
					return result + 1;
				});
		System.out.println(cf.join());
		System.out.println("time= " + Duration.between(startTime, Instant.now()).toMillis());
	}
}
