package com.test.study.util.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalTest {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static void simpleDateFormatTest() {

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                String date = dateFormat.format(new Date());
                System.out.println(date);
            }
        };

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }
}
