package life.jcy.king;

public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		
		Accumulation accumulation = new Accumulation();
		TestRunnable r1 = new TestRunnable(accumulation);
		TestRunnable r2 = new TestRunnable(accumulation);
		TestRunnable r3 = new TestRunnable(accumulation);
		
		new Thread(r1, "tom").start();
		new Thread(r2, "jerry").start();
		new Thread(r3, "lucky").start();
		
	}
}
