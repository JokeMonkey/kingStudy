package life.jcy.king;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @date :2023/1/12
 * @description :
 * @author: moji
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        for(int i = 0; i < 20; i++){
            new Thread(()->{
                System.out.println(String.format("[%s]start parking!!!", Thread.currentThread().getName()));
                try {
                    semaphore.acquire();
                    int time = new Random().nextInt(10);
                    System.out.println(String.format("[%s] stay in  there for %s second", Thread.currentThread().getName(), time));
                    TimeUnit.SECONDS.sleep(time);
                    semaphore.release();
                    System.out.println(String.format("[%s] over!!", Thread.currentThread().getName()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
