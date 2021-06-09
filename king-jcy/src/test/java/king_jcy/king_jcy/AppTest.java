package king_jcy.king_jcy;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AppTest {
	
	
	
	public static void main(String[] args) {
		LinkedHashMap linkedHashMap = new LinkedHashMap(16, 0.75f, true);
		linkedHashMap.put("key1", "123");
		linkedHashMap.put("key2", "456");
		linkedHashMap.put("key3", "789");
		linkedHashMap.put("wewew", "123");
		linkedHashMap.put("dsfds", "456");
		linkedHashMap.put("rewr", "789");
		
//		//linkedHashMap.get("key1");
//		Set<Entry> set = linkedHashMap.entrySet();
//		for(Entry entry : set) {
//			System.out.println("key:" + entry.getKey() + "|value:" + entry.getValue());
//		}
//		
//		
//		for(Iterator<Map.Entry<Integer,Integer>> it = linkedHashMap.entrySet().iterator();it.hasNext();){
//			System.out.println(it.next().getKey());
//		}
		
		
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("123", "456");
		
		
		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
		concurrentHashMap.put("wife", "jcy");
		
		int n = 8;
		System.out.println(n << 1);
		
		
		
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		
		
		lock.unlock();
		
		
		
		String aa = "123456";
		
		
	}
	
}
