package com.test.study.util.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArraysTest {

    /**
     * list.size与实际的数据量一致
     */
    public static void arraysize() {
        List<String> list = new ArrayList<>(100);
        System.out.println(list.size());
        list.add("e");
        System.out.println(list.size());
    }

    /**
     * 
     * list.add(int i,String a) i 不能大于实际数据量
     */
    public static void arrayAdd() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add(2, "b");
        for (String s : list) {
            System.out.println(s);
        }
    }
}