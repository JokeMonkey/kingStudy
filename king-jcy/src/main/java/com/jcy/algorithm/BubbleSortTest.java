package com.jcy.algorithm;

public class BubbleSortTest {
	public static void main(String[] args) {
		int[] arr = {8,2,6,1,9,3,1};
		int[] arrTemp = bubbleSort2(arr);
		for(int i = 0; i < arrTemp.length; i++) {
			System.out.print(arrTemp[i] + ", ");
		}
	}
	
	
	public static int[] bubbleSort1(int[] arr) {
		if(arr == null || arr.length == 0) {
			return arr;
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length - 1 - i; j++ ) {
				if(arr[j] < arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
		return arr;
	}
	
	public static int[] bubbleSort2(int[] arr) {
		if(arr == null || arr.length == 0) {
			return arr;
		}
		
		for(int i = 0; i < arr.length; i++) {
			printArr(arr);
			for(int j = i + 1; j < arr.length; j++ ) {
				if(arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
	}
	
	
	/**
	 * 打印数组
	 * 
	 * @param arr
	 */
	public static void printArr(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int k = 0; k < arr.length; k++) {
			sb.append(String.valueOf(arr[k])).append(",");
		}
		
		System.out.println(sb.toString().substring(0, sb.length() - 1));
	}
}
