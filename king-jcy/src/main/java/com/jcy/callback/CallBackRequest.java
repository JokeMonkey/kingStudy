package com.jcy.callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * 主调函数
 * 
 * */
public class CallBackRequest implements CallBack {
	//获取用户基本信息
	private CallBackResponse callBackResponse;
	
	CallBackRequest(CallBackResponse callBackResponse){
		this.callBackResponse = callBackResponse;
	}
	
	
	//获取用户信息
	public void getUserInfo(String userId) {
		
		System.out.println(userId + "|主调方法获取用户信息");
		
		Map<String, String> result = new HashMap<>();
		
		new Thread(()->{
			callBackResponse.hanlder(this, result);
		}).start();
		
		afterAsk(result);
		
	}
	
	
	@Override
	public void solve(Map<String, String> result, int ret) {
		//回调方法
		System.out.println("CallBackResponse进行回调，获取结果如下");
		System.out.println("ret:" + ret);
		
		Set<Entry<String, String>> entrySet = result.entrySet();
		for(Entry<String, String> entry : entrySet) {
			System.out.println("{key:" + entry.getKey() + ", value:" + entry.getValue() + "}");
		}
		
		System.out.println();
		
		if(ret == 0) {
			result.put("status", "Good");
		}else {
			result.put("status", "Bad");
		}

	}
	
	//增加用户地址信息
	public void afterAsk(Map<String, String> result) {
		System.out.println("进入获取用户地址的方法。。。。。。。。。");
		//获取用户地址
		result.put("address", "beijing");
		Set<Entry<String, String>> entrySet = result.entrySet();
		for(Entry<String, String> entry : entrySet) {
			System.out.println("{key:" + entry.getKey() + ", value:" + entry.getValue() + "}");
		}
	}
}
