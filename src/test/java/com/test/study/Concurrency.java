package com.test.study;

import com.test.study.util.concurrency.*;
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

    @Test
    public void ForkJoinTest(){
        ForkJoinTest forkJoinTest=new ForkJoinTest();
        forkJoinTest.test();
    }

	@Test
	public void countDownLatchTest() throws InterruptedException {
		CountDownLatchTest.countDownLatchTest(10);
	}

	@Test
	public void cyclicBarrierTest() throws InterruptedException {
		CyclicBarrierTest.cyclicBarrierTest(3);
	}


	@Test
    public void futureTest1() throws ExecutionException, InterruptedException {
        FutureTaskTest.futureTest1();
    }

	@Test
	public void futureTest2() throws ExecutionException, InterruptedException {
		FutureTaskTest.futureTaskTest();
	}

	@Test
	public void completedFutureTest() throws ExecutionException, InterruptedException {
    	CompletedFutureTest.completedFutureTest();
	}

	@Test
	public void exceptionallyTest() throws ExecutionException, InterruptedException {
		CompletedFutureTest.exceptionally();
	}

	@Test
	public void futureTest3() throws ExecutionException, InterruptedException {
		FutureTaskTest.futureTest2();
	}

	@Test
	public void testCompletionService() throws ExecutionException, InterruptedException {
		CompletionServiceTest.testCompletionService();
	}

}
