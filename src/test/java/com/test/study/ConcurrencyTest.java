package com.test.study;

import com.test.study.util.concurrency.BlockingQueueTest;
import com.test.study.util.concurrency.HashmapTest;
import com.test.study.util.concurrency.NewBank;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ConcurrencyTest {

    @Test
    public void newBankTest() throws InterruptedException {
        int count = 2;
        NewBank newBank = new NewBank(count, 100);
        for (int i = 0; i < 10000; i++) {

            Runnable runnable = () -> {
                Random random = new Random();
                int amount = random.nextInt(20);
                int to = random.nextInt(count);
                int from = random.nextInt(count);
                newBank.transfer(from, to, amount);

            };
            Thread thread = new Thread(runnable);
            thread.start();

        }
        Thread.sleep(10000);
        newBank.printBalance();
    }


    @Test
    public void hashmapTest() {
        HashmapTest.hashmaptest();
    }

    @Test
    public void addBlockingQueue() throws InterruptedException {
        BlockingQueueTest.addBlockingQueue();
    }
}
