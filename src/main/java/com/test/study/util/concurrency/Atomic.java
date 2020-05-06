package com.test.study.util.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;



/**
 * @author wlm
 */

@SuppressWarnings(value={"all"})
public class Atomic {

    public static AtomicLong atomicLong=new AtomicLong();

    public static void main(String[] args){
        Runnable runnable=()->{};
    }

    public static void atomicAndIncrement(){
        atomicLong.getAndIncrement();
        atomicLong.incrementAndGet();

    }

    public static void longAdder(){
        var addr=new LongAdder();

    }


    public static void count(){
        ExecutorService service= Executors.newCachedThreadPool();
        
    }
}
