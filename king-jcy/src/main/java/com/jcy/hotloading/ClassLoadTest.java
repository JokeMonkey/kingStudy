package com.jcy.hotloading;

/**
 * 描述:
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        new Thread(new ClassHandler()).start();
    }
}
