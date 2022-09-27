package com.jcy.hotloading;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 加载 manager 的工厂
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
public class ManagerFactory {

    /* 记录加载类的信息   <类名， 类信息> */
    private static final Map<String, LoadInfo> loadClassMap = new HashMap<>();

    /* 加载类的classPath */
    private static final String CLASS_PATH = "/Users/wangyaoyao/workSpace/kingStudy/king-jcy/target/classes/";
    /* 实现热加载 类的全路径 */
    public static final String MY_MANAGER = "com.jcy.hotloading.MyManager";

    /* 开始加载类 */
    public static Manager getManager(String className){
        File loadFile = new File(CLASS_PATH + className.replaceAll(".", "/") + ".class");
        //获取文件的最后修改时间
        long lastModified = loadFile.lastModified();
        //判断类是否被加载过
        if(loadClassMap.get(className) == null){
            load(className, lastModified);
        }else if(lastModified != loadClassMap.get(className).getLoadTime()){
            //判断类是否已经被更新
            load(className, lastModified);
        }
        return loadClassMap.get(className).getManager();
    }


    /**
     * 用类加载器去加载对应的类
     * @param className
     * @param lastModified
     */
    private static void load(String className, long lastModified){
        MyClassLoader myClasslLoader = new MyClassLoader(className);
        Class loadClass = null;
        try{
            loadClass = myClasslLoader.loadClass(className);
            Manager manager = (Manager) loadClass.newInstance();
            //Manager manager = newInstance(loadClass);
            LoadInfo loadInfo = new LoadInfo(myClasslLoader, lastModified);
            loadInfo.setManager(manager);
            loadClassMap.put(className, loadInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 以反射的方式创建 Manager 的子类
     *
     * @param loadClass
     * @return
     */
    private static Manager newInstance(Class loadClass){

        try{
            return (Manager)loadClass.getConstructor(new Class[] {}).newInstance(new Object[]{});
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
