package com.test.study.util.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * MyCrashHandler
 */
public class MyCrashHandler implements UncaughtExceptionHandler{


    public static void main(String[] args){
        test1();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
        System.out.println(e);


    }


    //捕获多线程中的错误
    public static void test1() {
        Runnable run = () -> {
            int a = 1 / 0;
            System.out.println(a);
        };

        Thread thread = new Thread(run);
//        thread.setUncaughtExceptionHandler(new MyCrashHandler());
        thread.start();
    }
    
}