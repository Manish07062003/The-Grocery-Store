<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<%
if (request.getParameter("userType") != null && request.getParameter("userType").equals("shopkeepers")) {
%>
<form action="queryItems" method="post" id="query-form" class="form"
	style="margin: auto; margin-top: 5rem;"
	onsubmit="return validateQueryForm()">
	<div>
		<label for="start-date">Start Date</label> <input type="date"
			name="start-date" id="start-date" />
	</div>
	<div>
		<label for="end-date">End Date</label> <input type="date"
			name="end-date" id="end-date" />
	</div>
	<div>
		<p class="form-error" id="query-error-para"></p>
		<button class="success" type="submit">Show Result</button>
	</div>
</form>
<form action="userOrderItems" method="post" id="user-form" class="form"
	style="margin: auto; margin-top: 5rem;"
	onsubmit="return validateUserForm()">
	<div>
		<label for="username">Username</label> <input type="text"
			name="username" id="username" placeholder="Enter Username" />
	</div>
	<div>
		<p class="form-error" id="user-error-para"></p>
		<button class="success" type="submit">Show Result</button>
	</div>
</form>
<%
}
%>
<div class="cart-main-container" style="margin: 3rem auto;">
	<h1 class="success-para" align="center">
		<%
		String msg = request.getParameter("msg");
		if (msg != null && !msg.equals("null")) {
			out.println(msg);
		} else {
			out.println("Purchased Items");
		}
		%>
	</h1>
	<c:set var="totalCost" value="0" scope="request" />
	<c:set var="totalQty" value="0" scope="request" />
	<c:forEach var="i" items="${ItemList}">
		<div class="cart-container">
			<div class="cart-inner-container" style="width: 100%">
				<div class="cart-image">
					<img src="${i.item_image}" alt="no image found">
				</div>
				<div class="cart-title">${i.item_name}</div>
				<div class="cart-category">${i.category_name}</div>
				<div class="cart-price">Rs.${i.item_price}</div>
				<div class="cart-total-details">
					<div class="cart-quantity" align="center">
						<div>Quantity</div>
						<div class="form-error">${i.item_qty }</div>
						<c:set var="totalQty" value="${totalQty+ 1}" />
					</div>
					<div class="cart-cost" align="center">
						<div>Cost</div>
						<div class="card-price">Rs.${i.total_cost }</div>
						<c:set var="totalCost" value="${totalCost+ i.total_cost}" />
					</div>
				</div>
				<div>
					<%
					if (request.getParameter("userType") != null && request.getParameter("userType").equals("shopkeepers")) {
					%>
					<div>By ${i.username }</div>
					<%
					}
					%>
					<div style="margin-top: 1rem;">On ${i.order_date}</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div style="font-size: 1.4rem; font-weight: bold;">
		Total Cost Of All Purchased Items Is <span
			style="color: green; font-weight: bolder;">Rs.${totalCost}</span>
	</div>
	<div style="font-size: 1.4rem; font-weight: bold;">
		Total Quantity Of All Purchased Items Is <span
			style="color: red; font-weight: bolder;">${totalQty}</span>
	</div>
</div>
<%@ include file="footer.html"%>