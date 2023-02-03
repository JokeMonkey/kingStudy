package com.jcy.agent;

import java.lang.instrument.Instrumentation;

/**
 * 描述: 定义Agent对象
 *
 * @date :2022/9/30
 * @description :
 * @author: moji
 */
public class DefaultAgent {

    /**假如你希望在main方法执行之前执行，就这样定义方法*/
    public static void premain(String args, Instrumentation inst){
        System.out.println("premain->"+args);
        inst.addTransformer(new DefaultClassTransformer(),true);
    }


    /**
     * 这种方式是要以attach的方式进行载入，然后在java程序启动后执行。
     * @param args
     * @param inst
     */
    public static void agentmain(String args, Instrumentation inst){
        System.out.println("agentmain->"+args);
        inst.addTransformer(new DefaultClassTransformer(),true);
        try {
            //指明哪些类需要重新加载
            inst.retransformClasses(CycleService.class);
        }catch (Exception e){
            System.out.println("agent error");
        }
    }

}
