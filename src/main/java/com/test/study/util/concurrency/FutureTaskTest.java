package com.test.study.util.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
}
