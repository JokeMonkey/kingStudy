package com.jcy.model.producer;

public class MiddlewareChannel {
	public int init_capacity=0;//初始存储  
	public int max_capacity=200;//最大存储 最大容量值
	
	/** 生产者 */
	public synchronized void send() {
		if(init_capacity > max_capacity) {
			//队列已经满了
			try {
				System.out.println("库存已经满了，生产者等待消费者消费消息。。。。。。");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("生产者" + Thread.currentThread().getName() + "发送消息,现在的消息容量为" + (++init_capacity));
			//唤醒消费者
			notifyAll();
		}
	}
	
	
	/** 消费者 */
	public synchronized void accept() {
		if(init_capacity <= 0) {
			//没有消息可消费
			try {
				System.out.println("库存已空，消费者等待生产者生产消息。。。。。。");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("消费者" + Thread.currentThread().getName() + "消费消息,现在的消息容量为" + (--init_capacity));
			//唤醒消费者
			notifyAll();
		}
	}
	
}
