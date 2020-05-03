package com.test.study.util.concurrency;

/**
 * run
 */
public class run {

    public static void main(String[] args) {
        test();
    }

    //多线程例子
    public static void test() {
        Runnable run = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i=" + i);
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }



    
}