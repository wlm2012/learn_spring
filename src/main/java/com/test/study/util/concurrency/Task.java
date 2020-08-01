package com.test.study.util.concurrency;

import java.util.concurrent.*;

public class Task {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            int result = (int) (Math.random() * 100);
            System.out.println(result);
            return result;
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(callable);
        System.out.println(future.get());


        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        executorService.submit(futureTask);
        System.out.println(futureTask.get());


    }
}
