package com.jcy.king;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrTest {
    public static void main(String[] args) {
//        String str = readToString("D:/TempFile/str.txt");
//        
//        System.out.println(str.length());
//        System.out.println("=============================================");
//        System.out.println(str);
        
        //System.out.println(sumNum(100));
        
        
        List<User> users = new ArrayList<>();
        User u1 = new User();
        u1.setId(1);
        u1.setName("Tom");
        u1.setSex("M");
        
        User u2 = new User();
        u2.setId(1);
        u2.setName("Tom");
        u2.setSex("M");
        
        User u3 = new User();
        u3.setId(2);
        u3.setName("Jetty");
        u3.setSex("M");
        
        User u4 = new User();
        u4.setId(3);
        u4.setName("jack");
        u4.setSex("M");
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        
        //将不重复的数据放在新的集合中
        List<User> temp = new ArrayList<>();
        //存放唯一值，例如User中的id为唯一值 
        Set<Integer> unique = new HashSet<>();
        for(User user : users){
            if(!unique.contains(user.getId())){
                unique.add(user.getId());
                temp.add(user);
            }
        }
        //输出新的集合
        for(User user : temp){
            System.out.println("id:" + user.getId());
        }
    }
    
    public static int sumNum(int num){
        int sum = num;
        num--;
        if(num > 0){
            return sum + sumNum(num); 
        }else{
            return sum;
        }    
    }
    
    
    public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
