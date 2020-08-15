package com.test.study;

import com.test.study.util.concurrency.Atomic;
import com.test.study.util.concurrency.ExecutorServiceTest;
import com.test.study.util.concurrency.FutureTaskTest;
import com.test.study.util.concurrency.ThreadLocalTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class Concurrency {

    @Test
    public void atomic() {
        Atomic.atomicTest();
    }

    @Test
    public void longAccumulatorTest() {
        Atomic.longAccumulatorTest();
    }


    @Test
    public void ThreadLocalTest() {
        ThreadLocalTest.simpleDateFormatTest();
    }

    @Test
    public void updateMap() {
        Atomic.updateMap();
    }

    @Test
    public void newKeySetTest() {
        Atomic.newKeySetTest();
    }

    @Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {
        FutureTaskTest.futureTest();
    }


    @Test
    public void executorServiceTest() throws ExecutionException, InterruptedException {
        ExecutorServiceTest.executorServiceTest();
    }

}
