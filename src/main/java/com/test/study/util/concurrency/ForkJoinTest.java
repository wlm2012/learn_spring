package com.test.study.util.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.DoublePredicate;

public class ForkJoinTest {

    public void test() {
        final int SIZE = 10000000;
        var numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        var counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        var pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
        System.out.println(pool.getPoolSize());

    }

    class Counter extends RecursiveTask<Integer> {

        public static final int THRESHOLD = 1000;
        private double[] values;
        private int from;
        private int to;
        private DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }


        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for (int i = from; i < to; i++) {
                    if (filter.test(values[i])) count++;
                }
                return count;
            } else {
                int mid = (from + to) / 2;
                var first = new Counter(values, from, mid, filter);
                var second = new Counter(values, mid, to, filter);
                invokeAll(first, second);
                return first.join() + second.join();
            }

        }
    }


}
