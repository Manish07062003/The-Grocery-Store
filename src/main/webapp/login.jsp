<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="jakarta.servlet.*"%>
<%@ page import="jakarta.servlet.http.*"%>
<%@ include file="header.jsp"%>
<div class="form-container">
	<%
	String msg = request.getParameter("msg");
	if (msg != null) {
	%>
	<h2 class="error-para hide-after-5s" align="center">
		<%
		out.println(msg);
		%>
	</h2>
	<%
	}
	%>
	<form action="login" class="form" method="post" id="login-form"
		onsubmit="return validateLoginForm()">
		<h1 class="center">Login</h1>
		<c:if test="${failMessage != null}">
			<div>
				<h2 class="form-error">${failMessage}</h2>
			</div>
		</c:if>
		<div>
			<label for="userType">Choose User Type</label> <select
				name="userType" id="userType">
				<option value="buyers">Buyer</option>
				<option value="shopkeepers">Shopkeeper</option>
			</select>
		</div>
		<div>
			<label for="username">Username</label> <input type="text"
				name="username" id="username">
		</div>
		<div>
			<label for="password">Password</label> <input type="password"
				name="password" id="password">
		</div>
		<div>
			<p class="form-error" id="login-error-para"></p>
			<button class="success" type="submit">login</button>
		</div>
		<div>
			<a href="registration.jsp">New User Register</a>
		</div>
	</form>
</div>

<%@ include file="footer.html"%>