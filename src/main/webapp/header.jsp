<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="jakarta.servlet.*"%>
<%@ page import="jakarta.servlet.http.*"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>

<body>
	<%
	Cookie[] cookies = null;
	Cookie cookie = null;
	cookies = request.getCookies();
	if (cookies != null) {
		String cookieName1 = "userType";
		String cookieName2 = "username";
		boolean username = false;
		String userType = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals(cookieName1)) {
		userType = cookie.getValue();
			} else if (cookie.getName().equals(cookieName2)) {
		username = true;
			}
		}
	%>
	<nav class="nav-container flex-between" id="navbar">
		<div class="nav-items flex-between linear-gradient-background">
			<img src="./images/logo.png" alt="No image found" id="logo">
			<h2>Organic</h2>
		</div>
		<ul class="nav-items flex-between" id="middle-items">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="shopDisplay">Shop</a></li>
			<li><a href="displayOrders">Orders</a></li>
			<%
			if (userType != null && userType.equals("shopkeepers")) {
			%>
			<li><a href="addItems.jsp">Add Item</a></li>
			<%
			}
			%>
		</ul>
		<ul class="nav-items flex-between" id="last-items">
			<%
			if (!username && userType == null) {
			%>
			<li class="border-right login-items"><a href="login.jsp"><img
					class="login-icon"
					src="https://img.icons8.com/external-febrian-hidayat-gradient-febrian-hidayat/64/000000/external-login-user-interface-febrian-hidayat-gradient-febrian-hidayat.png" />
					Login</a></li>
			<li class="border-right"><a href="registration.jsp">Signup</a></li>
			<%
			} else {
			%>
			<li class="border-right logout-items"><a href="logout"><img
					class="logout-icon"
					src="https://img.icons8.com/external-febrian-hidayat-gradient-febrian-hidayat/64/000000/external-logout-user-interface-febrian-hidayat-gradient-febrian-hidayat.png" />
					Logout</a></li>
			<%
			}
			} else {
			%>
			<li class="border-right login-items"><a href="login.jsp"><img
					class="login-icon"
					src="https://img.icons8.com/external-febrian-hidayat-gradient-febrian-hidayat/64/000000/external-login-user-interface-febrian-hidayat-gradient-febrian-hidayat.png" />
					Login</a></li>
			<li class="border-right"><a href="registration.jsp">Signup</a></li>
			<%
			}
			%>

			<li class="cart-items"><a href="displayCart"><img
					class="cart-icon"
					src="https://img.icons8.com/external-febrian-hidayat-gradient-febrian-hidayat/64/000000/external-cart-ui-essential-febrian-hidayat-gradient-febrian-hidayat.png" />

					Cart</a></li>
		</ul>
	</nav>