package com.test.study.util.concurrency;

import java.util.Arrays;

/**
 * @author wlm
 */
public class NewBank {

    private final double[] accounts;

    public NewBank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        if (from==to){
            return;
        }
        System.out.println(getTotalBalance());
        accounts[from] -= amount;
        accounts[to] += amount;
        System.out.println(getTotalBalance());
    }

    public double getTotalBalance() {
//        return Arrays.stream(accounts).sum();
        double total=0;
        for (double account:accounts) {
            total+=account;
        }
        return total;
    }

}
