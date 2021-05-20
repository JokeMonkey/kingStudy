package life.jcy.king;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {
	public static void main(String[] args) {
		
		TreeMap treeMap = new TreeMap();
		treeMap.put("a", "1");
		treeMap.put("c", "3");
		treeMap.put("b", "2");
		
		Set<String> set = treeMap.keySet();
		for(String key : set) {
			System.out.println(key + ":" + treeMap.get(key));
		}
		
	}
}
