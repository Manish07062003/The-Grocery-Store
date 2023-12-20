
/**
 * 
 */
const alphabets_regx = new RegExp("^[a-zA-Z ]*$");
const number_comma_regx = new RegExp("^[0-9,]*$");
const number_regx = new RegExp("^[0-9]*$");

function validateAddItemForm() {
	const form = document.querySelector("#add-item-form");
	const p = document.querySelector("#error-para");
	const name = form["item_name"].value;
	const price = form["item_price"].value;
	const category = form["item_category"].value;
	const image = form["item_img"].value;
	const qty = form["item_stock"].value;
	let err = "* ";
	if (name == "" || price == "" || category == "" || image == "" || qty == "") {
		err += "Enter All Fields";
	} else if (!alphabets_regx.test(name)) {
		err += "Enter Valid Name";
	} else if (!number_comma_regx.test(price)) {
		err += "Enter Valid Price";
	} else if (!number_regx.test(qty)) {
		err += "Enter Valid Quantity";
	}
	if (err == "* ") {
		return true
	}
	p.innerText = err;
	return false;
}

function validateRegisterForm() {
	const form = document.querySelector("#register-form");
	const p = document.querySelector("#register-error-para");
	const firstname = form["firstname"].value;
	const userType = form["userType"].value;
	const lastname = form["lastname"].value;
	const username = form["username"].value;
	const email = form["email"].value;
	const password = form["password"].value;
	const confirm_password = form["confirm-password"].value;
	const address = form["address"].value;
	let err = "* ";
	if (firstname == "" || lastname == "" || username == "" || password == "" || email == "" || address == "" || userType == "") {
		err += "Enter All Fields"
	} else if (!alphabets_regx.test(firstname)) {
		err += "Enter Valid Firstname";
	} else if (!alphabets_regx.test(lastname)) {
		err += "Enter Valid Lastname"
	} else if (password != confirm_password) {
		err += "Password Mismatched";
	}
	if (err == "* ") {
		return true;
	}
	p.innerText = err
	return false;
}
function validateQueryForm() {
	const form = document.querySelector("#query-form");
	const p = document.querySelector("#query-error-para");
	const start_date = form["start-date"].value;
	const end_date = form["end-date"].value;
	let err = "* ";
	if (start_date == "" || end_date == "") {
		err += "Enter All Fields";
	}
	if (err == "* ") {
		return true;
	}
	p.innerText = err;
	return false;

}
function validateSearchForm() {
	const form = document.querySelector("#search-form");
	const p = document.querySelector("#search-error-para");
	const search = form["search"].value;
	let err = "* ";
	if (search == "") {
		err += "Enter Item Name To Search";
	}
	if (err == "* ") {
		return true;
	}
	p.innerText = err;
	return false;

}
function validateLoginForm() {
	const form = document.querySelector("#login-form");
	const p = document.querySelector("#login-error-para");
	const username = form["username"].value;
	const password = form["password"].value;

	let err = "* ";
	if (password == "" || username == "") {
		err += "Enter All Fields";
	}
	if (err == "* ") {
		return true;
	}
	p.innerText = err;
	return false;

}
function validateUserForm(){
	const form = document.querySelector("#user-form");
	const p = document.querySelector("#user-error-para");
	const username = form["username"].value;
	let err = "* ";
	if (username == "") {
		err += "Enter All Fields";
	}
	if (err == "* ") {
		return true;
	}
	p.innerText = err;
	return false;

}
function incrementValue() {
	const number = this.document.activeElement.parentElement.firstElementChild;
	let value = parseInt(number.value);
	value++;
	number.value = value;
}
function decrementValue() {
	const number = this.document.activeElement.parentElement.firstElementChild;
	let value = parseInt(number.value);
	if (value > 1) {
		value--;
	}
	number.value = value;
}
setTimeout(() => {
	const boxes = document.getElementsByClassName("hide-after-5s")

	for (let box of boxes) {
		box.style.display = 'none';
	}

}, 3000);
