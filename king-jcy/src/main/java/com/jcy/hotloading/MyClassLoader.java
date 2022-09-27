package com.jcy.hotloading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;


/**
 * 描述: 自定义类加载器
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
public class MyClassLoader extends ClassLoader {

    /* 要加载的 Java 类的 classPath 路径 */
    private String classpath;

    public MyClassLoader(String classpath){
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }


    /**
     *
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try{
            name = name.replace(".", "/");
            inputStream = new FileInputStream(new File(classpath + name + ".class"));
            // 定义字节数组输出流
            outputStream = new ByteArrayOutputStream();
            int b = 0;
            while((b = inputStream.read()) != -1){
                outputStream.write(b);
            }
            return outputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(null != inputStream){
                    inputStream.close();
                }
                if(null != outputStream){
                    outputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }

}
