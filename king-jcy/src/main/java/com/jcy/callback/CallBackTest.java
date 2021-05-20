package com.jcy.callback;

public class CallBackTest {
	public static void main(String[] args) {
		CallBackResponse callBackResponse = new CallBackResponse();
		CallBackRequest callBackRequest = new CallBackRequest(callBackResponse);
		callBackRequest.getUserInfo("123456");
	}
}
