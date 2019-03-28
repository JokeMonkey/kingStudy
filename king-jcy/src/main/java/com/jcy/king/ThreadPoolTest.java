package com.jcy.king;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        cachedThreadExecutorTest();
    }
    
    
    //单通道线程池
    public static void singleThreadExecutorTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        MyThread myThread1 = new MyThread(1);
        MyThread myThread2 = new MyThread(2);
        MyThread myThread3 = new MyThread(3);
        MyThread myThread4 = new MyThread(4);
        
        executorService.execute(myThread1);
        executorService.execute(myThread2);
        executorService.execute(myThread3);
        executorService.execute(myThread4);
        
        executorService.shutdown();
    }
    
    //固定线程池
    public static void fixedThreadExecutorTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        MyThread myThread1 = new MyThread(1);
        MyThread myThread2 = new MyThread(2);
        MyThread myThread3 = new MyThread(3);
        MyThread myThread4 = new MyThread(4);
        
        executorService.execute(myThread1);
        executorService.execute(myThread2);
        executorService.execute(myThread3);
        executorService.execute(myThread4);
        
        executorService.shutdown();
    }
    
    //缓存线程
    public static void cachedThreadExecutorTest(){
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 1; i <= 800; i++){
            executorService.execute(new MyThread(i));
        }
        
        executorService.shutdown();
    }
    
    //自定义的线程示例
    private static class MyThread extends Thread {
        int num = 0;
        MyThread(int num){
            this.num = num;
        }
        
        
        public void run() {
            System.out.println(num + "---" + Thread.currentThread().getName()+"正在执行....");
        }
    }
}
