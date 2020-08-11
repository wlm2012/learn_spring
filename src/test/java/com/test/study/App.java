package com.test.study;

import com.test.study.util.concurrency.*;
import com.test.study.util.entity.Student;
import com.test.study.util.guava.OptionalTest;
import com.test.study.util.stream.StreamParallelDemo;
import com.test.study.util.stream.StreamTest;

import com.test.study.util.time.OldDate;
import com.test.study.util.work.XmlOutput;
import com.test.study.util.xml.XmlUtil;
import javassist.bytecode.CodeIterator;
import org.apache.coyote.http11.filters.VoidInputFilter;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

/**
 * Hello world!
 */
public class App {
    protected String s = "";

    /**
     * 与0xff相交，可以去除8位以上的数据
     */

    @Test
    public void test() {
        // Byte b=1;
        byte value = (byte) 0xff;
        int value1 = 0xff;
        System.out.println(value);
        System.out.println(value1);

    }

    @Test
    public void daemonTest() throws InterruptedException {
        Daemon.daemonTest();
    }


    @Test
    public void uncaughtTest() {
        UncaughtTest.uncaughtTest();
    }

    @Test
    public void completedFutureTest() throws InterruptedException {
        CompletedFutureTest.thenApplyExample();
    }


    @Test
    public void interruptTest() throws InterruptedException {
        InterruptTest.test01();
    }

    @Test
    public void streamTest() {
        // StreamTest.test();
        StreamTest.CollectorsStream();
        String s = null;
//        System.out.println(s.equalsIgnoreCase("1"));
        System.out.println("1".equalsIgnoreCase(s));
    }

    @Test
    public void streamTest01() {
        new StreamParallelDemo(1000_000_00);
    }

    @Test
    public void optionTest() {
        OptionalTest.optionTest();
    }

    @Test
    public void xmlOutputTest() throws Exception {
        XmlOutput.readFile();
    }

    @Test
    public void xmlUtilTest() throws IOException, SAXException, ParserConfigurationException {
        String path = "C:\\Users\\wlm\\Desktop\\a.txt";
        File file = new File(path);
        XmlUtil.xmlParse(file);
    }

    @Test
    public void extendsTest() {
        Student student = new Student();
        student.setId(1);
        student.setSex("1");
        System.out.println(student.getId());
        //student.id 无法直接访问
        System.out.println(student.sex);
    }

    @Test
    public void nullTest() {
        Object o = null;
        if ("2".equals(o)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


    @Test
    public void threadTest1() {
        ThreadTest test = new ThreadTest();
        test.threadTest();
    }

    @Test
    public void testCalendar() {
        Calendar checkBeginCal = Calendar.getInstance();
        checkBeginCal.set(Calendar.MONTH, 0);
        checkBeginCal.set(Calendar.DATE, 1);
        String checkBeginDate = OldDate.dateToString(checkBeginCal.getTime(), "yyyy-MM-dd");
        System.out.println(checkBeginDate);
    }

    @Test
    public void test1() {
        String a = null;
        try {
//			int z=0/0;
            if (a.equals("1")) {
                System.out.println("a");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() == null);
            System.out.println("null".equals(e.getMessage()));
//			System.out.println(e.getMessage().length());
        }

    }

    @Test
    public void test01() {
        StreamTest.CollectorsStream();
    }


    @Test
    public void test02(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.now().plusMonths(6).format(dtf));
        LocalDate localDate=LocalDate.parse("2020-08-31",dtf);
        System.out.println(localDate.plusMonths(6));
        System.out.println(LocalDate.parse("2020-02-11",dtf).plusMonths(6).isBefore(LocalDate.now()));


    }
}
