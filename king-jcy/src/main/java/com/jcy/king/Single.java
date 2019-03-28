package com.jcy.king;

public class Single {
    private static Single single = null;
    private Single(){}
    public static Single getSingle(){
        if(null == single){
            single = new Single();
        }
        return single;
    }
}
