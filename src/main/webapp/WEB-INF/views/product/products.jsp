<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
<p>User part</p>
<a href="${pageContext.request.contextPath}/">HomePage</a> |
<a href="${pageContext.request.contextPath}/products">Products</a> |
<a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
<a href="${pageContext.request.contextPath}/cart">Cart</a> |
<a href="${pageContext.request.contextPath}/user/orders">Orders</a> |
<a href="${pageContext.request.contextPath}/admin/index">Admin part</a> |
<a href="${pageContext.request.contextPath}/logout">Logout</a> |
<p>
<table border="1">
    <tr>
        <td><strong>ID</strong></td>
        <td><strong>Product name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Action</strong></td>
        <c:forEach var="product" items="${products}">
    <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><form action="${pageContext.request.contextPath}/cart/add" method="post">
            <input hidden name = "id" value="${product.id}">
            <input type="submit" value="buy">
        </form></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
</body>
</html>
