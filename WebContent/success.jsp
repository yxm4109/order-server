<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用叫号系统</title>
</head>
<body>
<div style="width:100%;text-align:center">
	<%= (String) request.getAttribute("result")%>
		<form action="OrderProcessServlet" method="post">

			请选择商家 <select name="userid">
				<%
					Set<String> s1 = (Set<String>) request.getAttribute("list");
					for (String str:s1) {
				%>
				<option value="<%=str%>"><%=str%></option>
				<%
					}
				%>
			</select> 
			<br /> 
			<br /> 
			<input type="submit" value="叫号" />
		</form>
	</div>
</body>
</html>



