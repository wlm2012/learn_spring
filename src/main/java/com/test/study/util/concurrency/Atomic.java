package com.test.study.util.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;


/**
 * @author wlm
 */


public class Atomic {

    public static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        Runnable runnable = () -> {
        };
    }

    public static void atomicAndIncrement() {
        atomicLong.getAndIncrement();
        atomicLong.incrementAndGet();

    }

    public static void longAdder() {
        var addr = new LongAdder();

    }


    public static void count() {
        ExecutorService service = Executors.newCachedThreadPool();

    }

    public static void atomicTest() {
        atomicLong.set(1);
        atomicLong.compareAndSet(2, 100);
        System.out.println(atomicLong.get());

        System.out.println(atomicLong.updateAndGet(s -> Math.max(s, 100)));

        System.out.println(atomicLong.accumulateAndGet(1, Math::min));

    }

    public static void longAccumulatorTest() {
        var adder = new LongAccumulator(Long::sum, 0);

        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adder.accumulate(random.nextInt(100));
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

        System.out.println(adder.get());
    }
}
