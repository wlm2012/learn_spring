package com.test.study.util.test;

public class TryCatch {
    public static void main(String[] args){
        TryCatch c = new TryCatch();
        c.method();
    }
    public void method(){
        try{
            System.out.println("A");
            int i = 10 / 0;
            System.out.println("B");
        }catch(Exception e){
            System.out.println("C");
        }finally{
            System.out.println("D");
        }
    }
}

