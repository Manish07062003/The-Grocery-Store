<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="cart-main-container">
	<h1 class="success-para" align="center">
		<%
		String msg = request.getParameter("msg");
		if (msg != null && !msg.equals("null")) {
			out.println(msg);
		} else {
			out.println("Cart Items");
		}
		%>
	</h1>
	<c:set var="totalCost" value="0" scope="request" />
	<c:set var="totalQty" value="0" scope="request" />
	<c:forEach var="i" items="${ItemList}">
		<div class="cart-container">
			<div class="cart-inner-container">
				<div class="cart-image">
					<img src="${i.item_image}" alt="no image found">
				</div>
				<div class="cart-title">${i.item_name}</div>
				<div class="cart-category">${i.category_name}</div>
				<div class="cart-price">Rs.${i.item_price}</div>
				<div>
					<form action="deleteItemCart" method="post">
						<input type="hidden" name="item_id" id="item_id"
							value=${ i.item_id }>
						<button type="submit" class="danger">Delete</button>
					</form>
				</div>
			</div>
			<div class="cart-total-details">
				<div class="cart-quantity" align="center">
					<div>Quantity</div>
					<div class="form-error">${i.item_qty }</div>
					<c:set var="totalQty" value="${totalQty+ i.item_qty}" />
				</div>
				<div class="cart-cost" align="center">
					<div>Cost</div>
					<div class="card-price">Rs.${i.total_cost }</div>
					<c:set var="totalCost" value="${totalCost + i.total_cost}" />
				</div>
			</div>
		</div>
	</c:forEach>
	<%
	if (msg == null) {
	%>
	<form action="checkout" id="checkout-form">
		<h3>
			Total Cost -
			<c:out value="${totalCost}" />
		</h3>
		<h3>
			Total Quantity -
			<c:out value="${ totalQty }" />
		</h3>
		<button class="success" type="submit">Checkout</button>
	</form>
	<%
	}
	%>
</div>
<%@ include file="footer.html"%>