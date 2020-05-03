package com.test.study.util.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.test.study.util.entity.Person;

public class Lambda {

    public static void test(String[] strings) {
        for (String string : strings) {

            System.out.println(string);
        }
    }

    public static void repeat() {
        repeat(10, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("hello world");
        });
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

    public static void checkAndExecute(List<Person>list, Predicate<Person> Predicate, Consumer<Person> consumer) {
        for (Person person : list) {
            if (Predicate.test(person)) {
                consumer.accept(person);
            }
        }
    }

    public static void testCheck() {
        List<Person> pList = Arrays.asList(new Person("qq", 11), new Person("ww", 22), new Person("ee", 13));
        pList.stream().filter((p) -> {
            return p.getName().startsWith("q");
        }).forEach(p -> {
            System.out.println(p.getName());
        });

        checkAndExecute(pList,p-> {
            return p.getName().startsWith("q");
        }, p -> {
            System.out.println(p.getName());
        });
    }

    public static void comparator() {
        List<Person> pList = Arrays.asList(new Person("qq", 11), new Person("ww", 22), new Person("ee", 13));
        Person[] persons = pList.stream().toArray(Person[]::new);
        Arrays.sort(persons, Comparator.comparing(Person::getOld));
        for (Person person : persons) {
            System.out.println(person.getName() + person.getOld());
        }
    }

}