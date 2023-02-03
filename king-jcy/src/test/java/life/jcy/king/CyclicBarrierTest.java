package life.jcy.king;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @date :2023/1/11
 * @description :
 * @author: moji
 */
public class CyclicBarrierTest {


    public static void main(String[] args) throws InterruptedException {
        test01();

    }




    public static void test01(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, ()->{
            System.out.println("------------------------------------");
        });
        for(int i = 0; i < 6; i++){
            int num = i;
            new Thread(()->{
                try{
                    System.out.println(String.format("运动员【%s】已经准备好！！！", Thread.currentThread().getName()));
                    cyclicBarrier.await();
                    System.out.println(String.format("运动员【%s】起跑------------>", Thread.currentThread().getName()));
                }catch (Exception e){

                }
            }, "thread-"+i).start();
        }

        System.out.println("--------OVER-------");
    }

    public static void test02() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 0; i < 10; i++){
            new Thread(()->{
                try{
                    System.out.println(String.format("运动员【%s】已经准备好！！！", Thread.currentThread().getName()));
                    countDownLatch.countDown();
                    System.out.println(String.format("运动员【%s】起跑------------>", Thread.currentThread().getName()));
                }catch (Exception e){

                }
            }, "thread-"+i).start();
        }
        countDownLatch.await();
        System.out.println("--------OVER-------");
    }


    public static void test03(){
        CyclicBarrierKing cyclicBarrierKing = new CyclicBarrierKing(3);
        for(int i = 0; i < 6; i++){
            new Thread(()->{
                try{
                    System.out.println(String.format("运动员【%s】已经准备好！！！", Thread.currentThread().getName()));
                    cyclicBarrierKing.await();
                    System.out.println(String.format("运动员【%s】起跑------------>", Thread.currentThread().getName()));
                }catch (Exception e){

                }
            }, "thread-"+i).start();
        }
    }
}
