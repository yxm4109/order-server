package com.mclab.mma.web;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mclab.mma.tools.Tools;
import com.mclab.mma.websocket.MessageCenter;
import com.mclab.mma.websocket.OrderManager;

/**
 * Servlet implementation class WebView
 */
@WebServlet("/OrderProcessServlet")
public class OrderProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderProcessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String result;

		try {
			String userId = (String) request.getAttribute("userid");
			userId = request.getParameter("userid");
			System.out.println("page selected userid="+userId);
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("type", "orderrequest");

			JSONObject dataObject = new JSONObject();
			String orderId = Tools.genOrderId(userId);
			dataObject.put("orderid", orderId);

			jsonObject.put("data", dataObject);

			OrderManager.getInstance().add(orderId, false);

			MessageCenter.getInstance().broadcastToOneUser(userId,
					CharBuffer.wrap(jsonObject.toString()));

			// 循环等待android端返回叫号结果
			int TIME_OUT = 20 * 1000;
			long startTime = System.currentTimeMillis();

			while (true) {
				if (OrderManager.getInstance().containsOrderIdNumMap(orderId)) {
					result = "叫号成功!" + "你的号码为："
							+ OrderManager.getInstance().getNum(orderId);
					break;
				}
				if (System.currentTimeMillis() - startTime > TIME_OUT) {
					result = "叫号超时";
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			result = "叫号失败";
		}

		MessageCenter mc = MessageCenter.getInstance();

		Set<String> data = mc.getAllConnUser();
		request.setAttribute("result", result);
		request.setAttribute("list", data);

		request.getRequestDispatcher("success.jsp").forward(request, response);

	}

}
