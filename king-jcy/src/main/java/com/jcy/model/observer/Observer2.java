package com.jcy.model.observer;


/**
 * 观察者2号
 * 
 * @author wangyaoyao.a
 *
 */
public class Observer2 extends Observer {

	@Override
	public void update() {
		System.out.println("观察者2号观察到了Subject的变化：state=" + subject.getState());
	}

}
