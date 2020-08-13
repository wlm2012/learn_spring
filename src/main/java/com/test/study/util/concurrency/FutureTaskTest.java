package com.test.study.util.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void futureTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("111");
            int i = 0;
            for (i = 0; i < 100_000; i++) {
//                Thread.sleep(100);
            }
            return i;
        };
        var futureTask = new FutureTask<Integer>(callable);
        var t = new Thread(futureTask);
        t.start();
//        Thread.sleep(100);
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.cancel(false));
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.isCancelled());
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
    }
}
