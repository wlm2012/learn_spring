package com.test.study.util.stream;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.test.study.util.entity.Person;

/**
 * StreamTest
 *
 * @author wlm
 */
public class StreamTest {

    // STREAM CREATION
    public static void creationStream() {
        String[] s = {"ww3", "qq", "ee"};

        // Arrays.asList .stream()
        List<String> words = Arrays.asList(s);
        long num = words.stream().filter(w -> w.length() > 2).count();
        System.out.println(num);

        // Stream.of
        Stream.of(s).forEach(System.out::println);

        // Arrays.stream(array, from, to)
        Arrays.stream(s, 1, 3).forEach(System.out::println);

    }

    public static void toMap() {
        Map<Integer, String> map = personStream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(map);
        HashMap<Integer, String> hashmap = personStream()
                .collect(Collectors.toMap(Person::getId, Person::getName, (existingValue, newValue) -> {
                    throw new IllegalStateException();
                }, HashMap::new));
        System.out.println(hashmap);

        HashMap<String, Set<String>> countryLanguageSets = Stream.of(Locale.getAvailableLocales())
                .collect(Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayCountry()),
                        (a, b) -> {
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }, HashMap::new));

    }

    public static Stream<Person> personStream() {
        return Stream.of(new Person(1, "name", 11), new Person(2, "qqq", 22), new Person(3, "www", 33));
    }

    public static void CollectorsStream() {
        String[] s = {"ww3", "qq", "ee"};
        String[] ss = Stream.of(s).toArray(String[]::new);
        List<String> list = Arrays.stream(s).collect(Collectors.toList());
        Set<String> set = Stream.of(s).collect(Collectors.toSet());
        TreeSet<String> treeSet = Stream.of(s).collect(Collectors.toCollection(TreeSet::new));
        String result = Arrays.stream(s).collect(Collectors.joining(","));

        List<Object> lObjects = new ArrayList<>();
        lObjects.add("qq");
        lObjects.add("www");
        lObjects.add("eeeee");
        lObjects.add(null);

        String result1 = lObjects.stream().filter(n -> null != n).map(Object::toString)
                .collect(Collectors.joining(","));
        IntSummaryStatistics Summary = lObjects.stream().filter(n -> null != n).map(Object::toString)
                .collect(Collectors.summarizingInt(String::length));

        System.out.println(ss);
        System.out.println(list);
        System.out.println(set);
        System.out.println(treeSet);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(Summary.getAverage());
        System.out.println(Summary.getMax());
    }

    public static void iterateStrem() {
        Integer[] integers = Stream.iterate(0, n -> n + 2).limit(10).toArray(Integer[]::new);
        Object[] objects = Stream.iterate(0, n -> n + 2).limit(10).toArray();
        System.out.println(integers.length);

        try {
            Integer[] numbers = (Integer[]) objects;
            System.out.println(numbers);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator);
        }

    }

    public static void generateInfiniteStream() {

        Stream<Double> stream = Stream.generate(Math::random);
        stream.forEach(System.out::println);
    }

    public static void flatmapTest() {
        String[] ss = {"hello welcome", "world hello", "hello world", "hello world welcome"};
        Stream<String> testStream = Stream.of(ss);
        testStream.map(s -> Arrays.stream(s.split(" "))).forEach(s -> System.out.println(Arrays.toString(s.toArray())));
        testStream = Stream.of(ss);
        testStream.flatMap(str -> Arrays.stream(str.split(" "))).forEach(strStream -> System.out.println(strStream));
    }

    public static void generateStream() {

        var limit = new BigInteger("10");
        Stream.iterate(BigInteger.ZERO, n -> n.compareTo(limit) < 0, n -> n.add(BigInteger.ONE))
                .forEach(System.out::println);

    }

    public static void ReduceStream() {

        String[] ss = {"hello welcome", "world hello", "hello world", "hello world welcome"};
        Stream<String> testStream = Stream.of(ss);
        System.out.println(testStream.count());

    }

    public static void test1() {

        String[] s = {"ww3", "qq", "ee"};
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