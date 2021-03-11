package com.jcy.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 
 * 栈转列表
 * 
 * @author wangyaoyao.a
 *
 */
public class StackToQueue {
	
	static Stack<Integer> data = new Stack<Integer>();
	static Stack<Integer> help = new Stack<Integer>();
	
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		
		System.out.println("======================================");
		add(1);
		add(2);
		add(3);
		System.out.println(peek());
		System.out.println(peek());
		System.out.println(peek());
	}
	
	
	
	public static void add(Integer value) {
		data.push(value);
	}
	
	public static Integer poll() {
		if(data.size() <= 0) {
			throw new RuntimeException("列表无元素");
		}
		
		while(data.size() > 0) {
			help.push(data.pop());
		}
		
		int result = help.pop();
		while(help.size() > 0) {
			data.push(help.pop());
		}
		
		return result;
	}
	
	public static Integer peek() {
		if(data.size() <= 0) {
			throw new RuntimeException("列表无元素");
		}
		
		while(data.size() > 0) {
			help.push(data.pop());
		}
		
		int result = help.peek();
		while(help.size() > 0) {
			data.push(help.pop());
		}
		
		return result;
	}
}
