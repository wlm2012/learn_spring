package com.test.study.util.concurrency;

import java.util.ArrayList;
import java.util.Random;

public class HashmapTest {

    static ArrayList<String> arrayList = new ArrayList<>();

    final static Random random = new Random();

    final static Object lock = new Object();

    public static void hashmaptest() {
        Runnable runnable = () -> {
            arrayList.add(random.nextInt(10000) + "");
        };

        Runnable runnable1 = () -> {
            synchronized (lock) {
                if (!arrayList.isEmpty()) {
                    arrayList.remove(0);
//                    System.out.println(Thread.currentThread().getName());
                }
            }
        };

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            Thread thread1 = new Thread(runnable1);
            thread1.setName("Thread---" + i);
            thread1.start();
        }


    }
}
