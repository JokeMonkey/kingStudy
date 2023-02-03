package com.jcy.agent;

import javassist.*;

import java.io.IOException;

/**
 * 描述:
 *
 * @date :2022/9/30
 * @description :
 * @author: moji
 */
/**
public class JavassistTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
        //获取CtClass对象的容器
        ClassPool cp = ClassPool.getDefault();
        //获取ctClass 这边是获取Base的类信息
        CtClass cc = cp.get("com.jcy.agent.Base");
        //获取ctClass 的类方法 也就是Base的 process 方法
        CtMethod cm = cc.getDeclaredMethod("process");
        //在方法前插入代码
        cm.insertBefore("{System.out.println(\"---start---\");}");
        cm.insertBefore("{long start = System.currentTimeMillis();}");
        //在方法后查入代码
        cm.insertAfter("{System.out.println(\"---end---\");}");
        //增加自定义方法
        CtMethod testMethod = CtNewMethod.make("public void test(){System.out.println(\"Hello World!\");}", cc);
        cc.addMethod(testMethod);

        //获取类的字节码对象
        Class baseClass = cc.toClass();

        //输出类的字节码文件
        cc.writeFile("/Users/wangyaoyao/dump");
        Base base = (Base)baseClass.newInstance();
        base.process();
    }
}
**/