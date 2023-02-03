package com.ycu.king.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 描述:字节码增强
 *
 * @date :2022/10/11
 * @description :
 * @author: moji
 */
public class MyClassTransformer implements ClassFileTransformer {

    private String packageName;

    MyClassTransformer(String packageName){
        System.out.println("初始化MyClassTransformer。。。。。。");
        this.packageName = packageName;
    }


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        //System.out.println(String.format("className=[%s]", className));
        className = className.replaceAll("/", ".");
        //针对目标包下进行耗时统计
        if(!className.contains(packageName)){
            return classfileBuffer;
        }
        System.out.println("-------------------开始进行指定包增强-------------------");
        //开始增强
        CtClass ctClass = null;
        try{
            ClassPool classPool = ClassPool.getDefault();
            ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            for(CtMethod ctMethod : ctClass.getDeclaredMethods()){
                //所有方法统计耗时 需要通过 addLocalVariable 方法来添加
                ctMethod.addLocalVariable("start", CtClass.longType);
                ctMethod.insertBefore("start = System.currentTimeMillis();");
                //ctMethod.insertBefore("System.out.println(\"之前\");");
                String methodName = ctMethod.getLongName();
                //ctMethod.insertAfter("System.out.println(String.format(\"%s的cost耗时：%s ms\", " + methodName + ", (System.currentTimeMillis() - start)));");
                ctMethod.insertAfter("System.out.println(\"" +methodName+ "cost\" + (System.currentTimeMillis() - start));");
                //ctMethod.insertAfter("System.out.println(\"之后\");");

            }

            byte[] transformed = ctClass.toBytecode();
            return transformed;
        }catch (Exception e){
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}
