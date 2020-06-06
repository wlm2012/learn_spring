package com.test.study.util.designPattern;


/**
 * @author wlm
 */
public class singleton {
    private volatile static singleton singleton;

    private singleton() {
    }

    public static singleton getSingleton() {
        if (singleton == null) {
            synchronized (singleton.class) {
                if (singleton == null) {
                    singleton = new singleton();
                }
            }
        }
        return singleton;
    }
}

