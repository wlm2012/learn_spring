package com.test.study.util.work;

import java.math.BigDecimal;

/**
 * DoubleChange
 */
public class DoubleChange {

    @SuppressWarnings(value={"deprecation"})
    public static double formatDouble(double d, int i) {
        BigDecimal decimal = BigDecimal.valueOf(d);
        d = decimal.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    public static void main(String[] args) {
        System.out.println(formatDouble(1.33554654, 2));
    }
}