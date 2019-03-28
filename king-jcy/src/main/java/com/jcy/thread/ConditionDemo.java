package com.jcy.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    static Lock lock = new ReentrantLock();
    
    
    volatile boolean flag = true;
    
    
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        Condition condition = lock.newCondition();
        
        new Thread(conditionDemo.new A(condition)).start();
        new Thread(conditionDemo.new B(condition)).start();
        
    }
    
    
    
    class A implements Runnable {
        Condition condition = null;
        
        A(Condition condition){
            this.condition = condition;
        }
        
        
        @Override
        public void run() {
            int i = 10;
            while(i > 0){
                lock.lock();
                try{
                    if(!flag){
                        System.out.println("my name is A!");
                        System.out.println("my name is A!");
                        i--;
                        flag = true;
                        condition.signal();
                    }else{
                        condition.awaitUninterruptibly();
                    }
                }catch(Exception e){
                    
                }finally{
                    lock.unlock();
                }
            }
        }
        
    }
    
    class B implements Runnable {
        
        Condition condition = null;
        
        B(Condition condition){
            this.condition = condition;
        }

        @Override
        public void run() {
            int i = 10;
            while(i > 0){
                lock.lock();
                try{
                    if(flag){
                        System.out.println("my name is B!");
                        i--;
                        flag = false;
                        condition.signal();
                    }else{
                        condition.awaitUninterruptibly();
                    }
                }catch(Exception e){
                    
                }finally{
                    lock.unlock();
                }
            }
        }
        
    }
}
