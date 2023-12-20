<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

<div class="form-container">
	<form action="updateItemStock" id="update-form" class="form">
		<h2 align="center">Update</h2>
		<div>
			<label for="item_stock">Enter New Stock</label> <input type="number"
				name="item_stock" id="item_stock" value=${item.item_stock}>
				<input type="hidden" name="item_id"	id="item_id" value=${item.item_id }>
		</div>
		<div>
			<button class="success" type="submit">Update</button>
		</div>
	</form>
</div>
<%@ include file="footer.html"%>