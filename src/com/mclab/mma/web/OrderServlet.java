package com.mclab.mma.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mclab.mma.websocket.MessageCenter;

/**
 * Servlet implementation class WebView
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		//MessageCenter.getInstance().broadcast( CharBuffer.wrap("hello"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageCenter mc = MessageCenter.getInstance();

		Set<String> data = mc.getAllConnUser();
		System.out.println("doPost data.size="+data.size());
		request.setAttribute("result", "");
		request.setAttribute("list", data);
		// request.setAttribute("result", "jiaohao");

		request.getRequestDispatcher("success.jsp").forward(request, response);
		
	}

}
