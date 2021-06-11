package com.jcy.model.observer;

public class ObserverPatternDemo {
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		new Observer1(subject);
		new Observer1(subject);
		
		System.out.println("First state change:15");
		subject.setState(15);
		System.out.println("Second state change:20");
		subject.setState(20);
	}
	
}
