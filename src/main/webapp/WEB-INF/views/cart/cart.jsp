<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Cart</h1>
<p>
    <a href="${pageContext.request.contextPath}/">HomePage</a> |
    <a href="${pageContext.request.contextPath}/products">Products</a> |
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
    <a href="${pageContext.request.contextPath}/cart">Cart</a>
</p>
<p>
<table border="1">
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <c:forEach var="item" items="${cart}">
    <tr>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.price}"/></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
</body>
</html>
