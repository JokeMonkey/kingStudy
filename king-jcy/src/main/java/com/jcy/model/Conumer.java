package com.jcy.model;

/**
 * 消费者
 * 
 * 
 * @author wangyaoyao.a
 *
 */
public class Conumer implements Runnable {
	

	MiddlewareChannel middlewareChannel;
	
	public Conumer(MiddlewareChannel middlewareChannel) {
		this.middlewareChannel = middlewareChannel;
	}
	
	@Override
	public void run() {
		while(true) {
			middlewareChannel.accept();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
