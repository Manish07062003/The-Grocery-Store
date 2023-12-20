<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="jakarta.servlet.*"%>
<%@ page import="jakarta.servlet.http.*"%>
<%@ include file="header.jsp"%>
<%
boolean username = false;
String userType = null;
cookies = request.getCookies();
if (cookies != null) {
	String cookieName1 = "userType";
	String cookieName2 = "username";
	for (int i = 0; i < cookies.length; i++) {
		cookie = cookies[i];
		if (cookie.getName().equals(cookieName1)) {
	userType = cookie.getValue();
		} else if (cookie.getName().equals(cookieName2)) {
	username = true;
		}
	}
}
%>
<form action="searchItem" method="post" id="search-form"
	onsubmit="return validateSearchForm()">
	<input type="text" name="search" id="search"
		placeholder="Search for an item">
	<button type="submit">
		<img id="search-icon"
			src="https://img.icons8.com/external-febrian-hidayat-gradient-febrian-hidayat/64/000000/external-search-user-interface-febrian-hidayat-gradient-febrian-hidayat.png" />
	</button>
	<br>
	<div style="margin-left:2rem;">
		<p class="error-para" id="search-error-para"></p>
	</div>
</form>
<%
String msg = request.getParameter("msg");
if (msg != null && !msg.equals("null")) {
%>
<h2 class="success-para hide-after-5s" align="center">
	<%
	out.println(msg);
	%>
</h2>
<%
} else {
%>
<form action="shopDisplay" method="post">
	<div>
		<label for="noOfItems">Choose No Of Items To Display</label> <select
			name="noOfItems" id="noOfItems">
			<option value="6">6</option>
			<option value="10">10</option>
			<option value="20">20</option>
		</select>
		<button class="success" type="submit">Show Results</button>
	</div>
</form>
<div class="card-main-container">
	<c:forEach var="i" items="${ItemList}">
		<div class="card-container">
			<div class="card-image">
				<img src="${i.item_image}" alt="no image found">
			</div>
			<div class="card-title">${i.item_name}</div>
			<div class="card-category">${i.category_name}</div>
			<div class="card-price">Rs.${i.item_price}</div>
			<div class="card-qty">Available Stock - ${i.item_stock}</div>
			<div class="card-modify-details">
				<%
				if (userType != null && userType.equals("shopkeepers")) {
				%>
				<form action="sendItem">
					<input type="hidden" name="item_id" value=${i.item_id }>
					<button class="primary">Update Stock</button>
				</form>
				<form action="deleteItem">
					<input type="hidden" name="item_id" value=${i.item_id }>
					<button class="danger">Delete Item</button>
				</form>

				<%
				} else {
				%>
				<div class="card-details">
					<form action="addToCart" method="post" id="cart-form">
						<input type="number" name="item_qty" value="1" id="number" /> <input
							type="button" onclick="decrementValue()" value="-" /> <input
							type="button" onclick="incrementValue()" value="+" />
						<button type="submit" class="warning">Add To Cart</button>
						<input type="hidden" name="item_id" value=${i.item_id }> <input
							type="hidden" name="item_price" value=${ i.item_price } />
					</form>
				</div>
				<%
				}
				%>

			</div>
		</div>
	</c:forEach>
</div>
<div class="pagination">
	<c:if test="${currentPage > 1}">

		<td><a href="shopDisplay?page=${currentPage - 1}">Previous</a></td>

	</c:if>
	<table border="1">

		<tr>

			<c:forEach begin="1" end="${noOfPages}" var="i">

				<c:choose>

					<c:when test="${currentPage eq i}">

						<td>${i}</td>

					</c:when>

					<c:otherwise>

						<td><a href="shopDisplay?page=${i}">${i}</a></td>

					</c:otherwise>

				</c:choose>

			</c:forEach>

		</tr>

	</table>
	<c:if test="${currentPage lt noOfPages}">

		<td><a href="shopDisplay?page=${currentPage + 1}">Next</a></td>

	</c:if>
</div>
<%
}
%>
<%@ include file="footer.html"%>