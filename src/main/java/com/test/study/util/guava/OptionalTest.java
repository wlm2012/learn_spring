package com.test.study.util.guava;

import com.google.common.base.Optional;

public class OptionalTest {

    public static void optionTest() {
        String s = null;
        Optional<String> optional = Optional.of(s);
        System.out.println(optional.get());
    }
}
