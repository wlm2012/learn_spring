package com.test.study;

import com.test.study.util.concurrency.Atomic;
import org.junit.jupiter.api.Test;

public class Concurrency {

    @Test
    public void atomic(){
        Atomic.atomicTest();
    }

    @Test
    public void longAccumulatorTest(){
        Atomic.longAccumulatorTest();
    }
}
