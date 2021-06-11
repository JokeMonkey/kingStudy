package com.jcy.model.producer;


/**
 * 
 * 生产者
 * 
 * @author wangyaoyao.a
 *
 */
public class Producer implements Runnable {
	
	
	MiddlewareChannel middlewareChannel;
	
	
	Producer(MiddlewareChannel middlewareChannel){
		this.middlewareChannel = middlewareChannel;
	}

	@Override
	public void run() {
		while(true) {
			middlewareChannel.send();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
