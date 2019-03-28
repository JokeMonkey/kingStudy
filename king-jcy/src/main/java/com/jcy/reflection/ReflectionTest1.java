package com.jcy.reflection;

public class ReflectionTest1 {
    
    public static void main(String[] args) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            //Class stu = Class.forName("com.jcy.reflection.Student");
            
            Class stu = loader.loadClass("com.jcy.reflection.Student");
            Student student = (Student) stu.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        
    }
}
