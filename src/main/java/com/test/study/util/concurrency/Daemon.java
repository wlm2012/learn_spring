package com.test.study.util.concurrency;

public class Daemon {

    public static void daemonTest() throws InterruptedException {

        Runnable runnable = () -> {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("daemon");
            }

        };

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(3010);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aa");
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);

        thread.setDaemon(true);
        thread.start();
        thread1.start();

        Thread.sleep(10000);
    }
}
