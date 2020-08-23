package com.test.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlm
 *
 * test Singleton Pattern
 */

@RestController
public class ConcurrencyTest {

    String s = null;

    @RequestMapping("/concurrencyTest")
    public String concurrencyTest(String time) throws InterruptedException {

        s = time;
        Thread.sleep(Integer.parseInt(time));
        return s;
    }
}
