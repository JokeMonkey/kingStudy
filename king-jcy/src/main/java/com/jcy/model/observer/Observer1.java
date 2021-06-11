package com.jcy.model.observer;



/**
 * 观察者1号
 * 
 * 
 * @author wangyaoyao.a
 *
 */
public class Observer1 extends Observer {
	
	
	public Observer1(Subject subject) {
		this.subject = subject;
		subject.attach(this);
	}

	@Override
	public void update() {
		System.out.println("观察者1号观察到了Subject的变化：state=" + subject.getState());
	}
	
}
