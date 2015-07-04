package com.mclab.mma.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.mclab.mma.websocket.MyMessageInbound;

/**
 * Servlet implementation class webStart
 */
@WebServlet("/WebSocketServelet")
public class WebSocketServelet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		System.out.println("##########client login#########");
		return new MyMessageInbound();
	}

}
