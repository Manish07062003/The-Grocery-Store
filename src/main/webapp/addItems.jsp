<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
    <div class="form-container">
        <form action="addNewItem" method="post" class="form" id="add-item-form" onsubmit="return validateAddItemForm()">
            <h1 class="center">Add Item</h1>
            <div>
                <label for="item_name">Name</label>
                <input type="text" name="item_name" id="item_name">
            </div>
            <div>
                <label for="item_category">Choose a Category</label>
                <select name="item_category" id="Category">
                    <option value="fruit">Fruit</option>
                    <option value="vegetable">Vegetable</option>
                    <option value="dairy">Dairy</option>
                    <option value="beverages">Beverages</option>
                    <option value="personal_care">Personal Care</option>
                    <option value="cleaners">Cleaners</option>
                    <option value="baking-goods">Baking Goods</option>
                </select>
            </div>
            <div>
                <label for="item_price" >Price</label>
                <input type="number" name="item_price" id="item_price">
            </div>
            <div>
                <label for="item_img">Image</label>
                <input type="url" name="item_img" id="item_img">
            </div>
            <div>
                <label for="item_stock">Stock</label>
                <input type="number" name="item_stock" id="item_stock">
            </div>
            <div>
                <p class="form-error" id="error-para"></p>
                <button class="success" type="submit">Add</button>
            </div>
        </form>
    </div>

    <%@ include file="footer.html" %>