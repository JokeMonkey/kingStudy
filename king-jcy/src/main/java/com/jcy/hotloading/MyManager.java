package com.jcy.hotloading;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 描述:
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
public class MyManager implements Manager {

    @Override
    public void execute() {
        System.out.println(LocalTime.now() + "      Java类热加载 ---------+++++++++++++++++++++-------- 哈哈哈哈");
    }
}
