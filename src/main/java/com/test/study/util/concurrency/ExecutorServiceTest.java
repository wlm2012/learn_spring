package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorServiceTest {

    public static void executorServiceTest() throws InterruptedException, ExecutionException {
        var tasks = new ArrayList<Callable<Long>>();
        Random random = new Random();
        Callable<Long> task = () -> {
            int i = 0;
            for (; i < random.nextInt(1000) + 1; i++) {
                Thread.sleep(1);
            }
            return (long) i;
        };

        for (int i = 0; i < 100; i++) {
            tasks.add(task);
        }

        Callable<Long> task1 = () -> {
            Thread.sleep(10);
            return (long) 0;
        };

        Callable<Long> task2 = () -> {
            Thread.sleep(100);
            return (long) 100;
        };

        tasks.add(task1);
        tasks.add(task2);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Instant startTime = Instant.now();
        List<Future<Long>> results = executorService.invokeAll(tasks);
        long total = 0;
        for (Future<Long> result : results) {
            total += result.get();
        }
        Instant endTime = Instant.now();
        System.out.println("time:   " + Duration.between(startTime, endTime).toMillis() + "   total:   " + total);
        System.out.println("pool size :    " + ((ThreadPoolExecutor) executorService).getLargestPoolSize());


    }
}
