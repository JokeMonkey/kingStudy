package com.jcy.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        
        new Thread(){
            public void run(){
                test.get(new Thread("线程1"));
            }
        }.start();
        
        new Thread(){
            public void run(){
                test.get(new Thread("线程2"));
            }
        }.start();
    }
    
    
    public void get(Thread thread){
        lock.writeLock().lock();
        
        try{
            int i = 5;
            while(i > 0){
                System.out.println(thread.getName()+"正在进行读操作");
                i--;
                Thread.sleep(1000);
            }
            System.out.println(thread.getName()+"读操作完毕");
        }catch(Exception e){
            
        }finally{
            lock.writeLock().unlock();
        }
        
    }
}
