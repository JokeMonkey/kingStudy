package com.jcy.callback;

import java.util.Map;

public class CallBackResponse {
	
	public void hanlder(CallBack callBack, Map<String, String> result) {
		int ret = 0;
		
		try {
			Thread.sleep(3000);
			//做一些其他的事情
			result.put("name", "Tom");
			result.put("age", "15");
			result.put("friend", "Jerry");
			result.put("name", "Tom");
		}catch (Exception e) {
			// TODO: handle exception
			ret = -99;
		}
		
		callBack.solve(result, ret);
	}
}
