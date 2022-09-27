package com.jcy.hotloading;

/**
 * 描述:
 *
 * @date :2022/9/27
 * @description :
 * @author: moji
 */
public class ClassHandler implements Runnable {

    @Override
    public void run() {
        while(true){
            Manager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.execute();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
