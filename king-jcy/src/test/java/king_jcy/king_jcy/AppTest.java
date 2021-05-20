package king_jcy.king_jcy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class AppTest {
	
	
	
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		
		lock.tryLock();
		
		lock.lock();
		
		lock.unlock();
		
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("key", "value");
		
		
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("key", "value");
		
	}
	
}
