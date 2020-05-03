package com.test.study;

import java.util.ServiceLoader;

import com.test.study.util.innerclass.OutClass;
import com.test.study.util.proxy.pay;
import com.test.study.util.stream.StreamTest;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

     @Test
     public void serviceLoader() {
         ServiceLoader<pay> pays=ServiceLoader.load(pay.class);
         for (pay pay : pays) {
             pay.print();
         }
        //  pays.stream().forEach(s->{});
        
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
