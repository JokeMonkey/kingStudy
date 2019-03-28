package com.jcy.reflection;

public class Student extends Person implements Study {
    
    public static String userNo = getUserNo();
    
    static{
        System.out.println("school is YCU");
    }
    
    public static String getUserNo() {
        System.out.println("执行学号。。。");
        return "20111102";
    }
    
    public static void excuteStatic(){
        System.out.println("执行静态方法。。。");
    }
    
    
    public String className;
    public String address;
    
    public Student() {}
    
    private String call(String message){
        return message;
    }
    
    public Student(String name, int age, String className, String address) {
        super(name, age);
        this.className = className;
        this.address = address;
    }
    
    public Student(String className) {
        this.className = className;
    }
    
    
    //----------------------set and get------------------
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
