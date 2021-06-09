package king_jcy.king_jcy;

import com.jcy.algorithm.ReverseLinked.Node;

public class Test {
	
	
	
	public Node mergerNode(Node<Integer> node1, Node<Integer> node2) {
		//首先获取获取第一个元素看看谁的比较大
		if(node1.value > node2.value) {
			
		}
		
		//用比较大的起很小的链表依次找比这个元素大的
		
		return null;
	}
	
	
	public void aa(Node<Integer> node1, Node<Integer> node2) {
		
		Node<Integer> node1Next = node1.next;
		Node<Integer> node2Next = node2.next;
		int bound = node2.value;
		Node<Integer> nodeStart = null;
		Node<Integer> nodeEnd = null;
		
		while(node1Next != null && node2Next != null) {
			
			while(node1Next != null && node1Next.value <= bound) {
				nodeStart = node1Next;
				node1Next = node1Next.next;
			}
			
			if(node1Next == null) {
				nodeStart.next = node2;
				return ;
			}
			
			while(node2Next != null && node2Next.value <= node1Next.value) {
				nodeEnd = node2Next;
				node2Next = node2Next.next;
			}

			nodeStart.next = node2;
			nodeEnd.next = node1Next;
			
			if(node2Next == null) {
				return ;
			}
			
			bound = node2Next.value;
		}
		
		
		
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
