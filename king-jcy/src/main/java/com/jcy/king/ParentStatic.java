package com.jcy.king;

import java.util.HashMap;
import java.util.Map;

public class ParentStatic {
    public ParentStatic(){
        System.out.println("父类构造方法。。。。");
    }
    public static void main(String[] args) {
        System.out.println(2<<1);
        System.out.println(2<<2);
        System.out.println(2<<3);
    }
}
