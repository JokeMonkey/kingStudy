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
		Node<Integer> head1 = reverseList(null, head);
		
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
