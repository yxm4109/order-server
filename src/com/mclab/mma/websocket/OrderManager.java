package com.mclab.mma.websocket;

import java.util.HashMap;

public class OrderManager {

	private HashMap<String, Boolean> orders = new HashMap<String, Boolean>();
	private static OrderManager orderManager = null;
	private HashMap<String, Integer> orderIdNumMap = new HashMap<String, Integer>();

	private OrderManager() {

		// for test

	}

	public static OrderManager getInstance() {
		if (orderManager == null) {
			orderManager = new OrderManager();
		}
		return orderManager;
	}

	public void add(String orderId, boolean isDealed) {
		synchronized (orders) {
			orders.put(orderId, isDealed);
		}
	}

	public void addToOrderIdNumMap(String orderId, int num) {
		synchronized (orderIdNumMap) {
			orderIdNumMap.put(orderId, num);
		}
	}

	public boolean containsOrderIdNumMap(String orderId) {
		return orderIdNumMap.containsKey(orderId);
	}

	public int getNum(String orderId) {
		return orderIdNumMap.get(orderId);
	}

	public boolean setIsDealed(String orderId, boolean isDealed) {
		synchronized (orders) {
			if (orderId.contains(orderId)) {
				orders.replace(orderId, isDealed);
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean getIsDealed(String oederId) {
		return orders.get(oederId);
	}

	public void remove(String userId) {
		synchronized (orders) {
			if (orders.containsKey(userId)) {
				orders.remove(userId);
			}
		}

	}

}
