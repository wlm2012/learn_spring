package com.test.study.util.exception;

import java.io.IOException;



//如果return一个值后，会覆盖原来的报错信息，开启testEx2()中的return，则testEx1()无法捕获错误
public class TestException {

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
            return ret;
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            // return ret;
        }
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            // return ret;
        }
    }

    boolean testEx2() throws IOException {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
                System.out.println("c=" + c);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw new IOException();
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            // ret=true;
            // return ret;
        }
    }

    public static void main(String[] args) {
        TestException testException1 = new TestException();
        try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}