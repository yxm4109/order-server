package com.mclab.mma.tools;

public class Tools {

	public static String genOrderId(String userId){
		return userId+System.currentTimeMillis();
	}
	
}
