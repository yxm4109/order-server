package com.mclab.mma.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Date;

import org.apache.catalina.ant.StopTask;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MyMessageInbound extends MessageInbound {

	String userId;

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
	}

	@Override
	protected void onTextMessage(CharBuffer msg) throws IOException {
		try {
			JSONObject json = new JSONObject(msg.toString());
			System.out.println("receive data=" + json.toString());
			String type = (String) json.get("type");
			JSONObject data = (JSONObject) json.get("data");
			if (type.equals("auth")) {
				checkUsers(data);
			} else if(type.equals("orderesponse")) {
				processOrderResponse(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void processOrderResponse(JSONObject data) {
		try {
			String result=data.getString("result");
			int num=data.getInt("num");
			String orderId=data.getString("orderid");
			if (result.equals("0")) {
				OrderManager.getInstance().addToOrderIdNumMap(orderId, num);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void close() {
		// // Do Nothing. close the connection
	

	}

	private void checkUsers(JSONObject authInfo) {
		try {

			userId = authInfo.getString("userid");
			String passwd = authInfo.getString("passwd");

			if (UserManager.getInstance().userAuthentication(userId, passwd)) {
				MessageCenter.getInstance().addMessageInbound(userId, this);
				System.out.println("checkUsers added user:"+userId);
			} else {
				close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onClose(int status) {
		System.out.println("close:" + new Date());
		MessageCenter.getInstance().removeMessageInbound(userId);
		super.onClose(status);
	}

	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("open :" + new Date());
		super.onOpen(outbound);
	}
}