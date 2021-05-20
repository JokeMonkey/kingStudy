package life.jcy.king;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Accumulation {
	Lock lock = new ReentrantLock();
	
	int a = 0;
	
	public void add() {
		try {
			lock.lock();
			for(int i = 0; i < 5; i++) {
				Thread.sleep(3000);
				a += 1;
				System.out.println(Thread.currentThread().getName() + "|A值的累加结果为：" + a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
	
}
