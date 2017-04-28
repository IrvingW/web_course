<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action = "Add_book_servlet" method="post">
	book name: <input type = "text" name = "book_name" class="box">
	author: <input type = "text" name = "author" class="box">
	price: <input type = "text" name = "price" class="box">
	stock: <input type = "text" name = "stock" class="box">
	
	<input type="submit" value="submit">
	</form>
	
	
	<form action = "Sign_up" method="post">
	user name: <input type="text" name="user_name">
	password: <input type="password" name="password">
	phone: <input type="text" name="phone">
	email: <input type="text" name="email">
	address: <input type="text" name="address">
	
	<input type="submit" value="submit">
	
	</form>
</body>
</html>