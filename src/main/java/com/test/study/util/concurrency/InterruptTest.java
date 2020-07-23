package com.test.study.util.concurrency;

/**
 * @author wlm
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test() throws InterruptedException {
        Runnable r = () -> {

            System.out.println("rr");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("r");

        };
        Thread thread = new Thread(r);
        thread.start();
        //注意Thread
//        Thread.sleep(1000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.interrupted());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getName() + "   " + thread.getState());
    }
}
