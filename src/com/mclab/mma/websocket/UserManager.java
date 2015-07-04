package com.mclab.mma.websocket;

import java.util.HashMap;

public class UserManager {

	private HashMap<String, String> users = new HashMap<String, String>();
	private static UserManager userManager = null;

	private UserManager() {

		//for test
		
		users.put("1234", "1234");
		users.put("12345", "12345");
		
	}

	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}
		return userManager;
	}

	public void add(String userId, String passwd) {
		users.put(userId, passwd);
	}

	public void remove(String userId) {
		if (users.containsKey(userId)) {
			users.remove(userId);
		}
	}

	public boolean userAuthentication(String userId, String passwd) {
		System.out.println("userid="+userId+" passwd="+passwd);
		if (users.containsKey(userId) && passwd.equals(users.get(userId)))
			return true;
		return false;
	}

}
