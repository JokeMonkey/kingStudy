package com.jcy.agent;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * 思考：
 * 1)所有class文件中的内容是什么? byte[]
 * 2)如何创建byte[]数组？ClassWriter
 * 3)字节数组byte[]中的内容是什么？接口信息、属性信息、方法信息。
 * 4)如何构建接口信息、属性信息、方法信息？FieldVisitor,MethodVisitor等对象
 */
/*
public class HelloJVMInterfaceGenerator {
    public static void main(String[] args) throws Exception {
        // (1) 生成byte[]内容
        byte[] bytes = generatorBytes();
        // (2) 保存byte[]内容到文件(class文件)
        File f = new File("/Users/wangyaoyao/workSpace/kingStudy/king-jcy/target/classes/com/jcy/agent/HelloJVMInterface.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(bytes);
        fout.close();
        //(3)加载类获取类的字节码对象(可选，检测类是否可以被加载到内存，语法是否有问题)
        Class<?> clazz = Class.forName("com.jcy.agent.HelloJVMInterface");
        System.out.println(clazz);
    }

    public static byte[] generatorBytes() throws Exception {
        // (1) 创建ClassWriter对象(此对象可以将类中信息写入到一个字节数组)
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // (2) 调用visitXxx()方法(创建类或接口)
        //创建接口类型信息 (public interface com.java.jvm.HelloJVMInterface extends Cloneable{} )
        cw.visit(
                Opcodes.V1_8,                                        // version
                Opcodes.ACC_PUBLIC
                        +Opcodes. ACC_ABSTRACT + Opcodes.ACC_INTERFACE,   // access
                "com/jcy/agent/HelloJVMInterface",                         // name
                null,                                        // signature
                "java/lang/Object",                          // superName
                new String[]{"java/lang/Cloneable"}                                         // interfaces
        );
        //创建接口内部属性 (public static final int a=-1)
        FieldVisitor fv1 = cw.visitField(Opcodes.ACC_PUBLIC +
                        Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "x", "I", null, -1);
        fv1.visitEnd();

        //创建接口内部方法(public abstract int say(Object obj);
        MethodVisitor mv1 = cw.visitMethod(Opcodes.ACC_PUBLIC +
                        Opcodes.ACC_ABSTRACT, "say",
                "(Ljava/lang/Object;)V",
                null, null);
        mv1.visitEnd();
        cw.visitEnd();

        // (3) 调用toByteArray()方法
        return cw.toByteArray();
    }
}
*/
