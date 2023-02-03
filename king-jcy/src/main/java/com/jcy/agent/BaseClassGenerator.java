package com.jcy.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:类生成器对象，基于这个类将ClassReader,ClassVisitor,ClassWriter组装在一起，
 *     然后修改类，并生成新的类
 *
 * @date :2022/9/29
 * @description :
 * @author: moji
 */
public class BaseClassGenerator {

    /*
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //第一步：构建ClassReader对象，读取指定位置的class文件(默认是类路径-classpath)
        ClassReader classReader =
                new ClassReader("com/jcy/agent/Base");

        //第二步：构建ClassWriter对象，基于此对象创建新的class文件
        //ClassWriter.COMPUTE_FRAMES 表示ASM会自动计算max stacks、max locals和stack map frame的具体内容。
        //ClassWriter.COMPUTE_MAXS 表示ASM会自动计算max stacks和max locals，但不会自动计算stack map frames。
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);//推荐使用COMPUTE_FRAMES

        //第三步：构建ClassVisitor对象，此对象用于接收ClassReader对象的数据，并将数据处理后传给ClassWriter对象
        ClassVisitor classVisitor = new BaseVisitor(classWriter);

        //第四步：基于ClassReader读取class信息，并将数据传递给ClassVisitor对象
        //这里的参数ClassReader.SKIP_DEBUG表示跳过一些调试信息等，ASM代码看上去就会更简洁
        //这里的参数ClassReader.SKIP_FRAMES表示跳过一些方法中的部分栈帧信息，栈帧手动计算非常复杂，所以交给系统去做吧
        classReader.accept(classVisitor,
                ClassReader.SKIP_DEBUG|ClassReader.SKIP_FRAMES);//推荐用这两个参数

        //第五步：从ClassWriter拿到数据，并将数据写出到一个class文件中
        byte[] data = classWriter.toByteArray();

        //构建内存中的字节码对象
//        String className="com.jcy.agent.Base";
//        Class aClass = new AsmClassLoader().defineClassFromByteCodes(className, data);
//        System.out.println(aClass);
//        Object obj= aClass.newInstance();
//        Method handle = aClass.getDeclaredMethod("process");
//        handle.invoke(obj);

        File file = new File("/Users/wangyaoyao/workSpace/kingStudy/king-jcy/target/classes/com/jcy/agent/Base.class");
        FileOutputStream ft = new FileOutputStream(file);
        ft.write(data);
        ft.close();

        Base base = new Base();
        base.process();
    }

    //自定义类加载器(可选)
    static class AsmClassLoader extends ClassLoader{

        public Class defineClassFromByteCodes(String className,byte[] byteCodes){
            return  defineClass(className, byteCodes, 0, byteCodes.length);
        }
    }*/

}
