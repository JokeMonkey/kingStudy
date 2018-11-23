package com.jcy.king;

public class StaticTest extends ParentStatic {
    
    public StaticTest() {
        //super();
        System.out.println("启动构造方法。。。。。。。。");
    }
    
    static{
        System.out.println("启动静态代码块");
    }
    
    public void test(){
        System.out.println("测试结束。。。");
    }
    
    public static void main(String[] args) {
        new StaticTest().test();
    }
}
