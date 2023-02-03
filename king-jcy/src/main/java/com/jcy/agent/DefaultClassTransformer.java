package com.jcy.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 描述: 字节码增强
 *
 * @date :2022/9/30
 * @description :
 * @author: moji
 */
public class DefaultClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);
        try{
            ClassPool cp = ClassPool.getDefault();
            CtClass cycleServiceCtc = cp.get("com.ycu.king.agent.CycleService");
            CtMethod ctMethod = cycleServiceCtc.getDeclaredMethod("doCycle");
            ctMethod.insertBefore("System.out.println(\"start\");");
            ctMethod.insertAfter("System.out.println(\"end\");");

            byte[] bytes = cycleServiceCtc.toBytecode();
            return bytes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
