package life.jcy.king;

public class ThreadYieldJoinTest {
	public static void main(String[] args) {
		//testYield();
		testWait();
	}
	
	
	
	public void testJoin() {
		Thread thread = new Thread(()->{
			System.out.println("yield test. current thread is " + Thread.currentThread());
			Long start = System.currentTimeMillis();
			int sum = 0;
			for(int i = 0; i < 100; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sum += i;
			}
			Long end = System.currentTimeMillis();
			
			System.out.println("总共耗时：" + (end - start));
			System.out.println("结果：" + sum);
		}, "Thread-Test");
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("等待thread执行后再执行");
	}
	
	
	public static void testYield() {
		Thread t1 = new Thread(()->{
			int sum = 0;
			Long start = System.currentTimeMillis();
			for(int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sum += i;
			}
			Long end = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "总共耗时：" + (end - start));
			System.out.println(Thread.currentThread().getName() + "结果：" + sum);
		}, "Tom");
		
		Thread t2 = new Thread(()->{
			int sum = 0;
			Long start = System.currentTimeMillis();
			for(int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sum += i;
			}
			Long end = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "总共耗时：" + (end - start));
			System.out.println(Thread.currentThread().getName() + "结果：" + sum);
		}, "Jerry");
		
		t1.start();
		t2.start();
		t1.yield();
	}
	
	
	public static void testWait() {
		ThreadYieldJoinTest threadYieldJoinTest = new ThreadYieldJoinTest();
		
		Thread t1 = new Thread(()->{
			synchronized (threadYieldJoinTest) {
				Long start = System.currentTimeMillis();
				try {
					threadYieldJoinTest.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Long end = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() + "总耗时：" + (end - start));
			}
		}, "Tom");
		
		Thread t2 = new Thread(()->{
			synchronized (threadYieldJoinTest) {
				Long start = System.currentTimeMillis();
				threadYieldJoinTest.notify();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Long end = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() + "总耗时：" + (end - start));
			}
		}, "Jerrt");
				
		t1.start();
		t2.start();
	}
}
