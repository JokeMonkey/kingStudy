package life.jcy.king;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @date :2023/1/11
 * @description :
 * @author: moji
 */
public class CyclicBarrierKing {

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();

    private int count;
    private final int parties;

    public CyclicBarrierKing(int count) {
        this.count = count;
        this.parties = count;
    }


    public void await(){
        try{
            reentrantLock.lock();
            int num = --count;
            System.out.println(String.format("[%s]线程进来，当前count值【%s】", Thread.currentThread().getName(), num));
            if(num == 0){
                //System.out.println("-----进来释放锁-----");
                condition.signalAll();
                this.count = parties;
            }else{
                condition.await();
            }

        }catch (Exception e){

        }finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new MyThread(reentrantLock).start();
        new MyThread(reentrantLock).start();


    }

    public static class MyThread extends Thread{
        private ReentrantLock reentrantLock;
        public MyThread(ReentrantLock reentrantLock){
            this.reentrantLock = reentrantLock;
        }

        @Override
        public void run(){
            try{
                reentrantLock.lock();
                String threadName = Thread.currentThread().getName();
                System.out.println(String.format("线程【%s】获取到锁！！！", threadName));
                Thread.sleep(5000);
            }catch (Exception e){

            }finally {
                reentrantLock.unlock();
            }
        }
    }
}
