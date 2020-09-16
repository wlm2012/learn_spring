package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class CountDownLatchTest {

    public static void countDownLatchTest(int i) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);

        Instant startTime = Instant.now();
        while (i > 0) {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            executor.execute(() -> {
                try {
                    System.out.println("execute1==="+i);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

            executor.execute(() -> {
                try {
                    System.out.println("execute2==="+i);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

            countDownLatch.await();
        }

        Instant endTime = Instant.now();
        System.out.println("time:   " + Duration.between(startTime, endTime).toSeconds());

    }


}
