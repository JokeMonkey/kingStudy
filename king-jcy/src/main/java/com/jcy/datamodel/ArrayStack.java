package com.jcy.datamodel;

public class ArrayStack {
    //定义一个数组
    private String[] arr;
    //数组的长度
    private int n;
    private int count;
    
    public ArrayStack(int n){
        this.arr = new String[n];
        this.n = n;
        this.count = 0;
    }
    
    //入栈
    public boolean push(String item) {
        //栈满返回false
        if(count == n){
            return false;
        }
        arr[count] = item;
        count++;
        return true;
    }
    //出栈
    public String pop(){
        //栈为空则返回空
        if(count == 0){
            return null;
        }
        String temp = arr[count-1];
        count--;
        return temp;
    }
    
}
