package com.test.study.util.concurrency;

import lombok.Data;

import java.sql.Time;
import java.util.concurrent.*;

public class FutureTaskTest {

	public static void futureTest() throws ExecutionException, InterruptedException {
		Callable<Integer> callable = () -> {
			System.out.println("111");
			int i = 0;
			for (i = 0; i < 100; i++) {
				Thread.sleep(10);
			}
			System.out.println("999");
			return i;
		};
		var futureTask = new FutureTask<>(callable);
		var t = new Thread(futureTask);
		t.start();
		Thread.sleep(500);
		System.out.println("isDone()" + futureTask.isDone());
		System.out.println("cancel()" + futureTask.cancel(false));
		System.out.println("isDone()" + futureTask.isDone());
		System.out.println("isCancelled()" + futureTask.isCancelled());
		System.out.println("isDone()" + futureTask.isDone());
		Thread.sleep(2000);
		System.out.println(futureTask.get());
	}


	public static void futureTest1() throws ExecutionException, InterruptedException {

		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> future = service.submit(
				() -> {
					TimeUnit.SECONDS.sleep(1);
					return 1;
				});
		System.out.println(future.get());

		FutureTaskTest futureTaskTest = new FutureTaskTest();
		Result result = futureTaskTest.new Result();

		Future<Result> future1 = service.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.setName("qwe");
		}, result);

		System.out.println(future1.get().getName());

	}

	@Data
	class Result {
		private int id;
		private String name;
	}

	public static void futureTaskTest() throws ExecutionException, InterruptedException {
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			TimeUnit.SECONDS.sleep(5);
			return 1 + 2;
		});
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.submit(futureTask);
		System.out.println(futureTask.get());


		FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
			TimeUnit.SECONDS.sleep(5);
			return 1 + 1;
		});
		Thread thread = new Thread(futureTask1);
		thread.start();
		System.out.println(futureTask1.get());
	}

}
