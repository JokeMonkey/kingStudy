package com.jcy.king;

import java.util.ArrayList;
import java.util.List;

public class CollectObjectTest {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("tom");
        user1.setAge(10);
        users.add(user1);
        
        User user2 = new User();
        user2.setId(2);
        user2.setName("jack.Ma");
        user2.setAge(10);
        user2.setSex("men");
        user2.setAddress("beijing");
        users.add(user2);
        
        change(users);
        
        
        for(User user : users){
            System.out.println("id:" + user.getId());
            System.out.println("name:" + user.getName());
            System.out.println("age:" + user.getAge());
            System.out.println("sex:" + user.getSex());
            System.out.println("address:" + user.getAddress());
            System.out.println("===================================");
        }
    }
    
    
    public static void change(Object obj){
        List<User> users = (List<User>) obj;
        
        List<User> change = new ArrayList<>();
        for(User user : users){
            if(user.getId() == 1){
                change.add(user);
            }
        }
        
        
        for(User user : change){
            user.setSex("women");
            user.setAddress("tianjin...");
        }
    }
}
