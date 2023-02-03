package com.jcy.spi;

/**
 * 描述:
 *
 * @date :2023/2/3
 * @description :
 * @author: moji
 */
public class SPIService1 implements SPIInterface{
    @Override
    public void doSomeThing() {
        System.out.println("hi boy01!!!");
    }
}
