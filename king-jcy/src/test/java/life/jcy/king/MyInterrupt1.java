package life.jcy.king;

public class MyInterrupt1 implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println("i=" + (i + 1));
			
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("通过this.isInterrupted()检测到中断");
                System.out.println("第一个interrupted()"+Thread.currentThread().interrupted());
                System.out.println("第二个interrupted()"+Thread.currentThread().interrupted());
                break;
			}
		}
		
		System.out.println("因为检测到中断，所以跳出循环，线程到这里结束，因为后面没有内容了");
	}

}
