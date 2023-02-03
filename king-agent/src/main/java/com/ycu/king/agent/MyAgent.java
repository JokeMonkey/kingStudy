package com.ycu.king.agent;

import java.lang.instrument.Instrumentation;

/**
*  描述:
* @date :2022/10/11
* @description :
* @author: moji
*/public class MyAgent {
    /**假如你希望在main方法执行之前执行，就这样定义方法*/
    public static void premain(String args, Instrumentation inst){
        System.out.println("premain->"+args);
        inst.addTransformer(new MyClassTransformer(args),true);
    }


    /**
     * 这种方式是要以attach的方式进行载入，然后在java程序启动后执行。
     * @param args
     * @param inst
     */
    public static void agentmain(String args, Instrumentation inst){

    }


}
