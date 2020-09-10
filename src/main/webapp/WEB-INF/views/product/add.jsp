<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product in Shop</title>
</head>
<body>
<h1>Add product</h1>
<p>
    <a href="${pageContext.request.contextPath}/">HomePage</a> |
    <a href="${pageContext.request.contextPath}/products">Products</a> |
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
    <a href="${pageContext.request.contextPath}/cart">Cart</a>
</p>
<p>
    <form method="post" action="${pageContext.request.contextPath}/products/add">
        Please provide product name: <input type="text" pattern=".{1,}" required  name="name"></br>
        Please provide product price: <input type="number" name="price" value="0"></br>
        <input type="submit" value="Add"></br>
    </form>
</p>
</body>
</html>
