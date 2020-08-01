package com.test.study.util.concurrency;

import java.util.concurrent.CompletableFuture;

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
}
