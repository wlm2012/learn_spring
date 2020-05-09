package com.test.study;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.ServiceLoader;

import com.test.study.util.arrays.ArraysTest;
import com.test.study.util.innerclass.OutClass;
import com.test.study.util.proxy.pay;
import com.test.study.util.stream.StreamTest;
import com.test.study.util.time.OldDate;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws ParseException
     */

    @Test
    public void oldTimeTest() throws ParseException {
        
        System.out.println(OldDate.stringToDate("2020-01-31 21:59:59","yyyy-MM-dd hh:mm:ss"));
        System.out.println(OldDate.dateToString(new Date(),"yyyy-MM-dd hh:mm:ss"));
        System.out.println(OldDate.stringToTimestamp("2020-01-31 21:59:59"));
        System.out.println(OldDate.timestampToString(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd hh:mm:ss"));
        System.out.println(OldDate.timestampToDate(new Timestamp(System.currentTimeMillis())));
        System.out.println(OldDate.dateToTimestamp(new Date()));
    }



    @Test
    public void arraysizeTest() {
        ArraysTest.arraysize();
    }

    @Test
    public void serviceLoader() {
        ServiceLoader<pay> pays = ServiceLoader.load(pay.class);
        for (pay pay : pays) {
            pay.print();
        }
        // pays.stream().forEach(s->{});

    }

    @Test
    public void shouldAnswerWithTrue() {
        StreamTest.test3();
    }

    @Test
    public void name() {
        String s = null;
        /*
         * if (s.equals("2")) {
         * 
         * }
         */

        if ("condition".equals(s)) {
            System.out.println("pass");
        } else {
            System.out.println("no");
        }
    }

    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        OutClass.Inner inner = outClass.new Inner();
        System.out.println(inner);
    }
}
