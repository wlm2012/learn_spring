package com.test.study.util.concurrency;


/**
 * @author wlm
 */

public class ThreadTest {

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double THREAD_NUM = 1000;
    public static final int DELAY = 10;

    public void threadTest() {
        NewBank newBank = new NewBank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < THREAD_NUM; i++) {
            Runnable r = () -> {
                int from = (int) (NACCOUNTS * Math.random());
                int to = (int) (NACCOUNTS * Math.random());
                double amount = INITIAL_BALANCE * Math.random();
                newBank.transfer(from, to, amount);
            };
            var t = new Thread(r);
            t.start();
        }
    }

}