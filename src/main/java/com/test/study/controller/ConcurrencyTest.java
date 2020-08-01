package com.test.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlm
 */

@RestController
public class ConcurrencyTest {

    @RequestMapping("/concurrencyTest")
    public String concurrencyTest(String time) throws InterruptedException {

        String s = null;
        s = time;
        Thread.sleep(Integer.parseInt(time));
        return s;
    }
}
