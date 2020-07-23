package com.test.study.util.concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wlm
 */
public class NewBank {

    private final double[] accounts;
    private final ReentrantLock banklock=new ReentrantLock();

    public NewBank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, int amount) {

        banklock.lock();
        try {
            if (accounts[from] < amount) {
                return;
            }
            if (from==to){
                return;
            }
            System.out.println(getTotalBalance());
            accounts[from] -= amount;
            System.out.printf(" %d from %d to %d  \n", amount, from, to);
            accounts[to] += amount;
            System.out.println(getTotalBalance());
        }finally {
            banklock.unlock();
        }

    }

    public double getTotalBalance() {
        return  Arrays.stream(accounts).sum();
//        result=Arrays.stream(accounts).collect(coll)
/*        double total=0;
        for (double account:accounts) {
            total+=account;
        }
        return total;*/
    }

}
