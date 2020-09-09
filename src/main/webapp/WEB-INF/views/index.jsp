<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Shop App</h1>
<p>
    <a href="${pageContext.request.contextPath}/">HomePage</a> |
    <a href="${pageContext.request.contextPath}/products">Products</a> |
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
    <a href="${pageContext.request.contextPath}/cart">Cart</a>
</p>
</body>
</html>
