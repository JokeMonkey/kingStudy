package com.jcy.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]] 
 * 输出：[[1,6],[8,10],[15,18]] 
 * 解释：
 * 区间[1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */

public class ArrMerge {
	
	public static void main(String[] args) {
		int[][] arr2 = new int [8][2];
		arr2[0][0] = 2;
		arr2[0][1] = 9;
		
		arr2[1][0] = 1;
		arr2[1][1] = 5;
		
		arr2[2][0] = 19;
		arr2[2][1] = 20;
		
		arr2[3][0] = 11;
		arr2[3][1] = 10;
		
		arr2[4][0] = 12;
		arr2[4][1] = 20;
		
		arr2[5][0] = 0;
		arr2[5][1] = 3;
		
		arr2[6][0] = 0;
		arr2[6][1] = 1;
		
		arr2[7][0] = 0;
		arr2[7][1] = 2;
		
		int[][] intervals = merge(arr2);
		
		
		for(int i = 0; i < intervals.length; i++) {
			for(int j = 0; j < intervals[i].length; j++) {
				System.out.print("|");
				System.out.print(intervals[i][j]);
			}
			
			System.out.println();
		}
		
	}
	
	
	public static int[][] merge(int[][] intervals){
		if(intervals.length == 0) {
			return new int[0][2];
		}
		
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		List<int[]> merged = new ArrayList<>();
		for(int i = 0; i < intervals.length; i++) {
			int L = intervals[i][0], R = intervals[i][1];
			if(merged.size() == 0 || (L > merged.get(merged.size() -1)[1])) {
				merged.add(new int[]{L, R});
			}else {
				if(R > merged.get(merged.size() -1)[1]) {
					merged.get(merged.size() - 1)[1] = R;
				}
			}
		}
		
		return merged.toArray(new int[merged.size() - 1][]);
	}

}
