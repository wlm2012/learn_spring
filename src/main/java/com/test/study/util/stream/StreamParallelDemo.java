package com.test.study.util.stream;

import java.util.ArrayList;
import java.util.Random;

public class StreamParallelDemo {

    public StreamParallelDemo(int j) {

        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < j; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(String.format("本计算机的核数：%d", Runtime.getRuntime().availableProcessors()));

        long time1 = System.currentTimeMillis();
        list.parallelStream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(System.currentTimeMillis() - time1);

        time1 = System.currentTimeMillis();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(System.currentTimeMillis() - time1);
    }
}
