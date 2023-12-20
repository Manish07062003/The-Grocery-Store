<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="jakarta.servlet.*"%>
<%@ page import="jakarta.servlet.http.*"%>
<%@ include file="header.jsp"%>
<div class="form-container">
	<form action="addUser" method="post" class="form" id="register-form" onsubmit="return validateRegisterForm()">
		<h1 class="center">Register</h1>
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
			<label for="firstname">FirstName</label> <input type="text"
				name="firstname" id="firstname">
		</div>
		<div>
			<label for="lastname">LastName</label> <input type="text"
				name="lastname" id="lastname">
		</div>
		<div>
			<label for="username">Username</label> <input type="text"
				name="username" id="username">
		</div>
		<div>
			<label for="email">Email</label> <input type="email" name="email"
				id="email">
		</div>
		<div>
			<label for="password">Password</label> <input type="password"
				name="password" id="password">
		</div>
		<div>
			<label for="confirm-password" >Confirm Password</label> <input
				type="password" name="confirm-password" id="confirm-password">
		</div>
		<div>
			<label for="address">Address</label> <input type="text"
				name="address" id="address">
		</div>
		<div>
			<p class="form-error" id="register-error-para"></p>
			<button class="success" type="submit">Register</button>
		</div>
		<div><a href="login.jsp">Already Registered Login</a></div>
	</form>
</div>

<%@ include file="footer.html"%>