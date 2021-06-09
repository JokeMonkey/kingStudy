package com.jcy.algorithm;

/**
 * 两个有序数组的合并
 * 
 * 
 * @author wangyaoyao.a
 *
 */
public class ArrMeger {
	
	
	static int[] result = new int[18];
	
	public static void main(String[] args) {
		int[] arr1 = new int[] {1,3,5,7,9};
		int[] arr2 = new int[] {2,4,6};
		
		merge(arr1, arr2, 0, 0, 0);
		
		for(int num : result) {
			System.out.println(num);
		}
		
	}
	
	
	public static void merge(int[] arr1, int[] arr2, int arr1Index, int arr2Index, int resultIndex) {
		
		int bound = arr2[arr2Index];
		while(arr1Index <= (arr1.length -1) && arr1[arr1Index] <= bound) {
			result[resultIndex] = arr1[arr1Index];
			resultIndex++;
			arr1Index++;
		}
		
		if(arr1Index <= (arr1.length -1)) {
			bound = arr1[arr1Index];
		}else {
			bound = arr1[arr1Index - 1];
		}
		
		while(arr2Index <= (arr2.length -1) && arr2[arr2Index] <= bound) {
			result[resultIndex] = arr2[arr2Index];
			resultIndex++;
			arr2Index++;
		}
		
		if(arr1Index > (arr1.length -1) && arr2Index <= (arr2.length -1)) {
			while(arr2Index <= (arr2.length -1)) {
				result[resultIndex] = arr2[arr2Index];
				resultIndex++;
				arr2Index++;
			}
			return ;
		}
		
		if(arr1Index <= (arr1.length -1) && arr2Index > (arr2.length -1)) {
			while(arr1Index <= (arr1.length -1)) {
				result[resultIndex] = arr1[arr1Index];
				resultIndex++;
				arr1Index++;
			}
			return ;
		}
		
		if(arr1Index > (arr1.length -1) && arr2Index > (arr2.length -1)) {
			return ;
		}
		
		merge(arr1, arr2, arr1Index, arr2Index, resultIndex);
	}
	
	
	
	
}
