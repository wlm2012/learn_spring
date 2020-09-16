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
                    Instant now = Instant.now();
                    System.out.println("execute1==="+i+"   time= " +Duration.between(startTime, now).toSeconds());
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

            executor.execute(() -> {
                try {
                    Instant now = Instant.now();
                    System.out.println("execute2==="+i+"   time= " +Duration.between(startTime, now).toSeconds());
                    TimeUnit.SECONDS.sleep(3);
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
