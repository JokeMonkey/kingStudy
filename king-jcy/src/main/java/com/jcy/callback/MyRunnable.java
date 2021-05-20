package com.jcy.callback;

import java.util.concurrent.CountDownLatch;

public class MyRunnable implements Runnable {

	private CountDownLatch countDownLatch;
	 
    private CountDownLatch await;
 
    public MyRunnable(CountDownLatch countDownLatch, CountDownLatch await) {
        this.countDownLatch = countDownLatch;
        this.await = await;
    }
 
    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("子线程" +Thread.currentThread().getName()+ "处理自己事情");
            Thread.sleep(1000);
            await.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);
 
        for (int i=0; i< 5; i++) {
            new Thread(new MyRunnable(countDownLatch, await)).start();
        }
 
        System.out.println("主线程处理自己事情");
        Thread.sleep(3000);
        countDownLatch.countDown();
        System.out.println("主线程处理结束");
        await.await();
        System.out.println("子线程处理完毕啦");
    }
}
