package com.mclab.mma.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.mclab.mma.websocket.MessageCenter;
import com.mclab.mma.websocket.MyMessageInbound;
import com.mclab.mma.websocket.UserManager;

@SuppressWarnings("deprecation")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -7178893327801338294L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// MessageCenter.getInstance().broadcast( CharBuffer.wrap("hello"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");

		String result = "0";

		try {
			String userId = (String) request.getAttribute("userid");
			String passwd = (String) request.getAttribute("passwd");

			UserManager.getInstance().add(userId, passwd);

		} catch (Exception e) {
			e.printStackTrace();
			result = "-1";
		}
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}