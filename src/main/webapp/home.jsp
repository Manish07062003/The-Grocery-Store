<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%
String mesg = request.getParameter("msg");
if (mesg != null && !mesg.equals("null")) {
%>
<h2 class="success-para hide-after-5s" align="center">
	<%=mesg%>
</h2>
<%
}
%>
<div class="container">
	<div class="home-main-container">
		<div>
			<h1 class="color">Welcome To Organic Grocery Store</h1>
		</div>
		<a href="shopDisplay"><button id="discover-button">Discover</button></a>
	</div>
</div>

<%@ include file="footer.html"%>