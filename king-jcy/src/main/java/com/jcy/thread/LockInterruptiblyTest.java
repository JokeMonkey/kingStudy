package com.jcy.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {
    private Lock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        LockInterruptiblyTest test = new LockInterruptiblyTest();
        MyThread m1 = new LockInterruptiblyTest().new MyThread(new Thread("Thread1"), test);
        MyThread m2 = new LockInterruptiblyTest().new MyThread(new Thread("Thread2"), test);
        
        m1.start();
        m2.start();
        
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            
        }
        
        m2.interrupt();
    }
    
    
    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();
        System.out.println(thread.getName()+"得到了锁");
        try{
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }catch(Exception e){
            
        }finally{
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
    
    
    class MyThread extends Thread{
        private Thread thread = null;
        private LockInterruptiblyTest test = null;
        
        MyThread(Thread thread, LockInterruptiblyTest test){
            this.thread = thread;
            this.test = test;
        }
        
        public void run(){
            try {
                test.insert(thread);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(thread.getName() + "中断了锁，你说气人不？");
            }
        }
    }
}
