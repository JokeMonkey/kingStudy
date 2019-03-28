package com.jcy.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        
        Class stu = null;
        
        try {
            stu = Class.forName("com.jcy.reflection.Student");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Student studentReflection = (Student) stu.newInstance();
        
        
        // 获取父类以及自身的公共属性
        Field[] fields = stu.getFields();
        for(Field field : fields){
            System.out.println("当前类以及父类公共属性:" + field.getName());
        }
        
        try {
            System.out.println("当前类以及父类公共属性:" + stu.getField("className").getName());
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //获取该对象的所有的公共属性 
        Field[] fieldAlls = stu.getDeclaredFields();
        for(Field field : fieldAlls){
            System.out.println("当前类指定公共属性:" + field.getName());
        }
        
        
        // 获取父类以及自身的公共方法
        Method[] methodAlls = stu.getMethods();
        for(Method method : methodAlls){
            System.out.println("父类以及自身的公共方法:" + method.getName());
        }
        
        //获取该对象的公共方法
        Method[] methods = stu.getDeclaredMethods();
        for(Method method : methods){
            System.out.println("自身的公共方法:" + method.getName());
        }
        
        //获取父类以及自身的构造方法
        Constructor[] constructorAlls = stu.getConstructors();
        for(Constructor constructor : constructorAlls){
            System.out.println("父类以及自身的构造方法：" + constructor.getName());
        }
        
        //获取该类的构造方法
        Constructor[] constructors = stu.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            System.out.println("自身构造方法：" + constructor.getName());
        }
        
        //获取该类的真实名称
        System.out.println("类名称：" + stu.getName());
        //获取该类的包名
        System.out.println("包名称：" + stu.getPackage().getName());
        //获取实现的接口
        Class[] interfaces = stu.getInterfaces();
        for(Class inter : interfaces){
            System.out.println("实现的接口名称" + inter);
        }
        
        //调用
        Student student = new Student("KING", 20, "1102", "TJ");
        try {
            //调用方法
            System.out.println("调用方法。。。。。。。");
            Method parentMethod = stu.getMethod("showInfo");
            Object object = parentMethod.invoke(student);
            System.out.println(object);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //私有方法
        try {
            Method call = stu.getDeclaredMethod("call", String.class);
            //代码入侵
            call.setAccessible(true);
            Object object = call.invoke(studentReflection, "懵逼");
            System.out.println("私有方法执行：" + object);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
