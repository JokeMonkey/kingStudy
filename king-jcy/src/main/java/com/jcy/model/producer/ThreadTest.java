package com.jcy.model.producer;

public class ThreadTest {
	
	public static void main(String[] args) {
		MiddlewareChannel moddle = new MiddlewareChannel();
		
		Producer p1 = new Producer(moddle);
		Thread tp1 = new Thread(p1, "P1");
		tp1.start();
		
		Producer p2 = new Producer(moddle);
		Thread tp2 = new Thread(p2, "P2");
		tp2.start();
		
		Conumer c1 = new Conumer(moddle);
		Thread tc1 = new Thread(c1, "C1");
		tc1.start();
		
		Conumer c2 = new Conumer(moddle);
		Thread tc2 = new Thread(c2, "C1");
		tc2.start();
	}
	
	
}
