package com.mclab.mma.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("deprecation")
public class MessageCenter {
	private static MessageCenter instance = new MessageCenter();

	private Map<String, MessageInbound> socketMap;

	private MessageCenter() {
		socketMap = new HashMap<String, MessageInbound>();
	}

	public static MessageCenter getInstance() {
		return instance;
	}

	public void addMessageInbound(String userId, MessageInbound inbound) {
		socketMap.put(userId, inbound);
	}

	public void removeMessageInbound(String userId) {
		socketMap.remove(userId);
	}
	
	public Set<String> getAllConnUser(){
		return socketMap.keySet();
	}

	public void broadcast(CharBuffer msg) throws IOException {
		for (Entry<String, MessageInbound> messageInbound : socketMap
				.entrySet()) {
			WsOutbound outbound = messageInbound.getValue().getWsOutbound();
			outbound.writeTextMessage(CharBuffer.wrap("broadcasting:" + msg));
			outbound.flush();
		}
	}

	public void broadcastToOneUser(String userId, CharBuffer msg)
			throws IOException {
		System.out.println("hello");

		System.out.println("hello2");
		WsOutbound outbound = socketMap.get(userId).getWsOutbound();
		outbound.writeTextMessage(CharBuffer.wrap(msg));
		outbound.flush();
	}
}