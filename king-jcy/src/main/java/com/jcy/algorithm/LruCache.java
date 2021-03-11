package com.jcy.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
	
	private int cap;
	private Map<Object, ListNode> cache;
	private ListNode head;
	private ListNode tail;
	
	public LruCache(int size){
		this.cap = size;
		this.head = null;
		this.tail = null;
		this.cache = new HashMap<>();
	}
	
	
	public static void main(String[] args) {
		LruCache lru = new LruCache(3);
		lru.put("1", "1");
		lru.put("2", "2");
		lru.put("3", "3");
		lru.get("1");
		lru.put("4", "4");
		
		
		System.out.println((String)lru.get("2"));
	}
	
	
	public Object get(Object key) {
		ListNode obj = cache.get(key);
		if(obj == null) {
			return obj;
		}
		
		moveToHead(obj);
		return obj.value;
	}
	
	
	public void put(Object key, Object value) {
		ListNode obj = cache.get(key);
		//如果对应的ListNode不存在,存在只更新value值
		if(obj == null) {
			if(cache.size() >= cap) {
				//大于定长移除尾元素
				cache.remove(tail.key);
				removeTailListNode();
			}
			obj = new ListNode();
			obj.key = key;
		}
		
		obj.value = value;
		moveToHead(obj);
		cache.put(key, obj);
	}
	
	
	public void removeNode(Object key) {
		ListNode node = cache.get(key);
		//如果对应的ListNode不存在,返回
		if(node == null) {
			return ;
		}
		
		if(node.pre != null) {
			node.pre.next = node.next;
		}
		
		if(node.next != null) {
			node.next.pre = node.pre;
		}
		
		if(node == head) {
			head = head.next;
		}
		
		if(node == tail) {
			tail = tail.pre;
		}
		
		cache.remove(key);
	}
	
	
	public void clear() {
		head = null;
		tail = null;
		cache.clear();
	}
	
	
	private void moveToHead(ListNode node) {
		//如果元素本身就在头部
		if(node == head) {
			return ;
		}
		
		//是把命中的元素从链表中取出来
		if(node.pre != null) {
			node.pre.next = node.next;
		}
		
		if(node.next != null) {
			node.next.pre = node.pre;
		}
		
		if(tail == node) {
			tail = tail.pre;
		}
		
		//如果列表中没有元素
		if(head == null || tail == null) {
			head = tail = node;
			return ;
		}
		
		//把名中的元素放到头部
		node.next = head;
		head.pre = node;
		head = node;
		node.pre = null;
	}
	
	
	public void removeTailListNode() {
		if(tail != null) {
			tail = tail.pre;
			if(tail == null) {
				head = null;
			}else {
				tail.next = null;
			}
		}
	}
	
	
	class ListNode{
		Object key;
		Object value;
		ListNode pre;
		ListNode next;
		
		public ListNode(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
		
		public ListNode() {}
	}
	
	
}
