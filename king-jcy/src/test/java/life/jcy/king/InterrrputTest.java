package life.jcy.king;

public class InterrrputTest {
	public static void main(String[] args) {

		/*
		* interrupt  线程中设置终止状态，并非把线程进行终止
		* isInterrupted  返回线程的终止状态
		* interrupted 当前线程是否被终止，被清除终止状态
		* */


		//testIsInterrupted();
		//testInterrupted();
		//testInterrupted1();
		testInterrupted2();
	}
	
	
	
	/**
	 * 测试IsInterrupted
	 */
	public static void testIsInterrupted() {
		MyInterrupt MyInterrupt = new MyInterrupt();
		Thread thread = new Thread(MyInterrupt);
		thread.start();
		thread.interrupt();


		System.out.println("第一次调用isInterrupted：" + thread.isInterrupted());
		System.out.println("第二次调用isInterrupted：" + thread.isInterrupted());
		System.out.println("thread是否存活：" + thread.isAlive());
	}
	
	
	/**
	 * 测试interrupted
	 */
	public static void testInterrupted() {
		MyInterrupt MyInterrupt = new MyInterrupt();
		Thread thread = new Thread(MyInterrupt);
		thread.start();
		thread.interrupt();
		
		System.out.println("第一次调用isInterrupted：" + thread.isInterrupted());
		System.out.println("第二次调用isInterrupted：" + thread.isInterrupted());
		System.out.println("第一次调用interrupt：" + thread.interrupted());//结果返回false
		System.out.println("第二次调用interrupt：" + thread.interrupted());//结果返回false
		/*
		 * 从输出结果看，可能会有疑惑，为什么后面两个interrupted方法输出的都是false，
		 * 而不是预料中的一个true一个false？注意！！！这是一个坑！！！上面说到，
		 * interrupted（）方法测试的是当前线程是否被中断，当前线程！！！
		 * 当前线程！！！这里当前线程是main线程，而thread.interrupt(）
		 * 中断的是thread线程，这里的此线程就是thread线程。
		 * 所以当前线程main从未被中断过，
		 * 尽管interrupted（）方法是以thread.interrupted（）的形式被调用，
		 * 但它检测的仍然是main线程而不是检测thread线程，所以thread.interrupted（）在这里相当于main.interrupted（）
		 * 
		 * */
	}
	
	
	/**
	 * 继续测试Interrupted
	 */
	public static void testInterrupted1() {
		Thread.currentThread().interrupt();//首先中断当前的线程
		System.out.println("第一次调用isInterrupted：" + Thread.currentThread().isInterrupted());
		System.out.println("第二次调用isInterrupted：" + Thread.currentThread().isInterrupted());
		System.out.println("第一次调用interrupt：" + Thread.currentThread().interrupted());//调用清除中断状态并返回true
		System.out.println("第二次调用interrupt：" + Thread.currentThread().interrupted());
		System.out.println("第三次调用interrupt：" + Thread.currentThread().interrupted());
	}
	
	
	public static void testInterrupted2() {
		MyInterrupt1 myInterrupt1 = new MyInterrupt1();
		Thread thread = new Thread(myInterrupt1);
		thread.start();
		thread.interrupt();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("myInterrupt1线程是否存活："+thread.isAlive());
	}
}
