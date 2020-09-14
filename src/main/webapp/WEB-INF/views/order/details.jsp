<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order information</title>
</head>
<body>
<h1>Order information</h1>
<p>User part</p>
<a href="${pageContext.request.contextPath}/">HomePage</a> |
<a href="${pageContext.request.contextPath}/products">Products</a> |
<a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
<a href="${pageContext.request.contextPath}/cart">Cart</a> |
<a href="${pageContext.request.contextPath}/user/orders">Orders</a> |

<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders/list">Orders list</a> |
</p>
<table border="1">
    <tr>
        <td><strong>Product ID</strong></td>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <c:forEach var="product" items="${products}">
    <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><form action="${pageContext.request.contextPath}/order" method="post">
            <input hidden name = "id" value="${product.id}">
            <input type="submit" value="show order">
        </form></td>
    </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td>${sum} uah</td>
    </tr>
</table>
</body>
</html>
