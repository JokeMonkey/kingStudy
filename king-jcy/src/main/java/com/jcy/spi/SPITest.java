package com.jcy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 描述: https://www.cnblogs.com/wuxun1997/p/13286949.html
 *      SPI的基础使用
 *
 * @date :2023/2/3
 * @description :
 * @author: moji
 */
public class SPITest {

    public static void main(String[] args) {
        ServiceLoader<SPIInterface> serviceLoader = ServiceLoader.load(SPIInterface.class);
        Iterator<SPIInterface> SPIInterfaces = serviceLoader.iterator();
        while(SPIInterfaces.hasNext()){
            SPIInterface spiInterface = SPIInterfaces.next();
            System.out.printf("loading %s\n", spiInterface.getClass().getName());
            spiInterface.doSomeThing();
        }
    }
}
