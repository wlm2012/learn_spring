package com.test.study.util.stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * StreamTest
 */
public class StreamTest {

    // STREAM CREATION
    public static void test() {
        String[] s = { "ww3", "qq", "ee" };

        // Arrays.asList .stream()
        List<String> words = Arrays.asList(s);
        long num = words.stream().filter(w -> w.length() > 2).count();
        System.out.println(num);

        // Stream.of
        Stream.of(s).forEach(System.out::println);

        // Arrays.stream(array, from, to)
        Arrays.stream(s, 1, 3).forEach(System.out::println);

    }

    public static void generateInfiniteStream() {
        
        var limit = new BigInteger("10");
        Stream.iterate(BigInteger.ZERO, n -> n.compareTo(limit) < 0, n -> n.add(BigInteger.ONE))
                .forEach(System.out::println);

    }

    public static void generateStream() {

        var limit = new BigInteger("10");
        Stream.iterate(BigInteger.ZERO, n -> n.compareTo(limit) < 0, n -> n.add(BigInteger.ONE))
                .forEach(System.out::println);

    }

    public static void test1() {

        String[] s = { "ww3", "qq", "ee" };
        List<String> words = new ArrayList<>();
        words = Arrays.asList(s);
        words.forEach(System.out::print);
    }

    public static void test2() {
        Stream<String> words = Stream.generate(() -> "word");
        System.out.println(words);
    }

    // 无限流会一直产生，即使没有输出
    public static void test3() {
        Stream<BigInteger> iStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        iStream.filter(n -> n.compareTo(BigInteger.valueOf(100)) < 0).forEach(System.out::print);
    }

}