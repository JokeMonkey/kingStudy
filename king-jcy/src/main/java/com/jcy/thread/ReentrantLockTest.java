package com.jcy.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    
    
    public static void main(String[] args) {
        final ReentrantLockTest reentrantLock = new ReentrantLockTest();
        new Thread(){
            public void run() {
                reentrantLock.insert(new Thread("线程1"));
            }
        }.start();
        
        new Thread(){
            public void run() {
                reentrantLock.insert(new Thread("线程2"));
            }
        }.start();
    }
    
    
    
    
    public void insert(Thread thread) {
        if(lock.tryLock()){
            try{
                System.out.println(thread.getName()+"获取了锁");
                for(int i = 0; i < 5; i++){
                    thread.sleep(1000);
                    arrayList.add(i);
                }
            }catch(Exception e){
                
            }finally{
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println(thread.getName() + "尝试获取锁，但是没有获取到。。。");
        }
    }
}
