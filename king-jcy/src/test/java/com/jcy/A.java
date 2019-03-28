package com.jcy;

public class A {
    public static int width = 100;
    public static final int higth = 200;
    
    static{
        System.out.println("执行静态方法。。。。。。");
        width = 300;
    }
    
    public A(){
        System.out.println("执行构造方法。。。。");
    }
    
    public static void main(String[] args) {
//        A a = new A();
        System.out.println(A.higth);
    }
}
