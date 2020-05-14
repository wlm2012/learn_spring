package com.test.study;

import com.test.study.util.stream.StreamTest;

import org.junit.jupiter.api.Test;

/**
 * Hello world!
 *
 */
public class App {
    protected String s = "";

    /**
     * 与0xff相交，可以去除8位以上的数据
     */

    @Test
    public void streamTest() {
        StreamTest.test();
    }

    @Test
    public void test() {
        // Byte b=1;
        byte value = (byte) 0xff;
        int value1 = 0xff;
        System.out.println(value);
        System.out.println(value1);

    }

}
