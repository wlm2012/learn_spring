package com.test.study.util.concurrency;

import java.util.Arrays;

public class UncaughtTest {

    public static void uncaughtTest() {
        UncaughtTest uncaughtTest = new UncaughtTest();
        ThreadTest threadTest = uncaughtTest.new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.setUncaughtExceptionHandler(new UncaughtTest().new UncaughtException());
        thread.start();
    }


    public class ThreadTest implements Runnable {

        @Override
        public void run() {
            System.out.println("begin");
            System.out.println(10 / 0);
            System.out.println("end");
        }
    }

    public class UncaughtException implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("this is wrong :" + e.getMessage());
            System.out.println("this is wrong :" + Arrays.toString(e.getStackTrace()));
        }
    }
}
