package com.jcy.algorithm;


//反转链表
public class ReverseLinked {
	
	public static void main(String[] args) {
		Node<Integer> head = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		
		head.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		//迭代处理
		//Node<Integer> head1 = reverseList(head);
		//Node<Integer> head1 = reverseList(null, head);
		
		//Node<Integer> head1 = reverseList1(head);//于2021年6月2日编写
		Node<Integer> head1 = reverseList2(null, head);
		
		Node<Integer> currentNode1 = null;
		currentNode1 = head1;
		while(currentNode1 != null) {
			System.out.println(currentNode1.value);
			currentNode1 = currentNode1.next;
		}
	}
	
	
	
	/**
	 * 迭代处理链表反转
	 * 
	 * @param head
	 * @return
	 */
	public static Node<Integer> reverseList(Node<Integer> head) {
		Node<Integer> prev = null;
		Node<Integer> currentNode = head;
		while(currentNode != null) {
			Node<Integer> nodeTemp = currentNode.next;
			currentNode.next = prev;
			prev = currentNode;
			currentNode = nodeTemp;
		}
		
		return prev;
	}

	
	
	/**
	 * 算法这个东西多写几遍真的不一样  N就以后第一次写
	 * 
	 * @Date 2021-06-02
	 * @param head
	 * @return
	 */
	public static Node<Integer> reverseList1(Node<Integer> head) {
		
		/*
		 * 执行过程 首次循环 pre为1->null    conCurrentNode为2->3->4->null
		 * 执行第二次循环 pre为2->1->null  conCurrentNode为3->4->null
		 * 
		 * */
		
		Node<Integer> conCurrentNode = head;
		Node<Integer> pre = null;
		//1->2->3->4->null
		while(conCurrentNode != null) {
			Node<Integer> nodeTemp = conCurrentNode.next;//2->3->4->null
			conCurrentNode.next = pre;//1->null
			pre = conCurrentNode;//1->null
			conCurrentNode = nodeTemp;//2-3>->4->null
		}
		
		return pre;
	}
	
	
	
	
	/**
	 * 递归处理 	
	 * 
	 * @param prev
	 * @param node
	 * @return
	 */
	public static Node<Integer> reverseList(Node<Integer> prev, Node<Integer> node){
		if(null == node) {
			return prev;
		}
		Node next = node.next;
		node.next = prev;
		return reverseList(node, next);
	}
	
	
	
	
	
	/**
	 * 递归处理  2021年 6月2号再次复盘
	 * 
	 * @Date 2021-06-02
	 * @param prev
	 * @param node
	 * @return
	 */
	public static Node<Integer> reverseList2(Node<Integer> prev, Node<Integer> node){
		if(node == null) {
			return prev;
		}
		
		Node<Integer> nodeNext = node.next;//2->3->4->null
		node.next = prev;//1->null
		return reverseList2(node, nodeNext);
	}
	
	
	
	//定义一个Node
	public static class Node<T> {

		public T value;

		public Node next;

		Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}

		Node(T value) {
			this.value = value;
		}

		Node() {
		}
	}
}
