package com.jcy.algorithm;

/**
 * 环形链表
 * 
 * 
 * @author wangyaoyao.a
 *
 */
public class DetectCycle {
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(0);
		ListNode node4 = new ListNode(-4);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node2;
		
		ListNode node = detectCycle1(node1);
		System.out.println(node.val);
		
	}

	
	
	
	
	
	public static ListNode detectCycle1(ListNode head) {
		ListNode fast;
		ListNode slow;
		ListNode index1;
		ListNode index2;
		fast = slow = head;
		if(head == null) {
			return null;
		}
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) {
				index1 = head;
				index2 = slow;
				while(index1 != index2) {
					index1 = index1.next;
					index2 = index2.next;
				}
				return index1;
			}
			
		}
		
		return null;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
