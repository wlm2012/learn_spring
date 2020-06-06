package com.test.study.util.test;

public class Inherance {
    public static void main(String[] args){
        ChildClass clazz = new ChildClass();
        ChildClass.staticMethod();
        clazz.method();
    }
}
class ParentClass{
    public ParentClass(){
        System.out.println("A");
    }
    public static void staticMethod(){
        System.out.println("B");
    }
    public void method(){
        System.out.println("C");
    }
}
class ChildClass extends ParentClass{
    public ChildClass(){
        System.out.println("X");
    }
    public static void staticMethod(){
        System.out.println("Y");
    }
    public void method(){
        System.out.println("Z");
    }
}

