package com.jcy.algorithm;

import java.util.HashMap;
import java.util.Map;

/**给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
* 输出: 3 
* 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
*/
public class Longest {
	
	public static void main(String[] args) {
		String str1 = "abcabcbb";
		String str2 = "bbbbb";
		String str3 = "pwwkew";
		String str4 = "dvdf";
		
		System.out.println(lengthOfLongestSubstring(str1));
		System.out.println(lengthOfLongestSubstring(str2));
		System.out.println(lengthOfLongestSubstring(str3));
		System.out.println(lengthOfLongestSubstring(str4));
	}
	
	
	public static int lengthOfLongestSubstring(String s) {
        if(null == s || s.length() <= 0) {
        	return 0;
        }
		
        char[] arr = s.toCharArray();
        Map<Object, Integer> map = new HashMap<>();
        int mostNum = 0;
        int startIndex = 0;
        for(int i = 0; i < arr.length; i++) {
        	if(map.keySet().contains(arr[i])) {
        		if((i-startIndex) > mostNum) {
        			mostNum = i-startIndex;
        		}
        		if(startIndex <= map.get(arr[i])) {
        			startIndex = map.get(arr[i]) + 1;
        		}
        	}
        	map.put(arr[i], i);
        }
        if((arr.length - startIndex) > mostNum) {
			mostNum = arr.length - startIndex;
		}
		
		return mostNum;
    }
}
