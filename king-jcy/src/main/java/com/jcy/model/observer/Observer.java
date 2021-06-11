package com.jcy.model.observer;



/**
 * 观察者抽象方法
 * 
 * 
 * @author wangyaoyao.a
 *
 */
public abstract class Observer {
	protected Subject subject;
	public abstract void update();
}
