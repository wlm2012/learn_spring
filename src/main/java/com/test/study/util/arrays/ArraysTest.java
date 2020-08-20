package com.test.study.util.arrays;

import java.util.*;

public class ArraysTest {

    /**
     * list.size与实际的数据量一致,和设定的容量无关
     */
    public static void arraysize() {
        List<String> list = new ArrayList<>(100);
        System.out.println(list.size());
        list.add("e");
        System.out.println(list.size());
    }

    /**
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

    public static void AsList() {
        String s = "aa,bb,cc";
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(s.split(",")));
        System.out.println(set);


        HashMap<String, String> map = new HashMap<>();
        map.put("aaa", "aa");
        map.put("bbb", "bb");
        map.put("ccc", "cc");
//        set.addAll(map);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("aaa");
        arrayList.add("bbb");
        arrayList.add("ccc");
        set.addAll(arrayList);
        System.out.println(set);
    }
}