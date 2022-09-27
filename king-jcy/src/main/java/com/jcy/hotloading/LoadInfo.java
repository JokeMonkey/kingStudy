package com.jcy.hotloading;

import lombok.Data;

/**
 * 描述:
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
@Data
public class LoadInfo {

    /* 自定义类加载器 */
    private MyClassLoader myClassLoader;

    /* 热加载的类 */
    private Manager manager;

    /* 记录要加载类的时间戳-》类的加载时间 */
    private long loadTime;


    public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }
}
