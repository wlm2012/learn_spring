package com.test.study.util.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class CountDownLatchTest {

    public static void countDownLatchTest(int i) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(4);

        Instant startTime = Instant.now();
        int finalI = i;
        while (i > 0) {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    Instant now = Instant.now();
                    System.out.println("thread 1 = " + Duration.between(startTime, now).toSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    Instant now = Instant.now();
                    System.out.println("thread 2 = " + Duration.between(startTime, now).toSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("=========");
            });



            executor.execute(() -> {
                try {
                    countDownLatch.await();
                    Instant now = Instant.now();
                    System.out.println("thread 3 = " + Duration.between(startTime, now).toSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
//            System.out.println("i===" + i);
            i--;
        }
        TimeUnit.SECONDS.sleep(finalI * 5 + 1);
        Instant endTime = Instant.now();
        System.out.println("time:   " + Duration.between(startTime, endTime).toSeconds());

    }


}
