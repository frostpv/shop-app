<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User orders</title>
</head>
<body>
<h1>User orders</h1>
<p>User part</p>
<a href="${pageContext.request.contextPath}/">HomePage</a> |
<a href="${pageContext.request.contextPath}/products">Products</a> |
<a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
<a href="${pageContext.request.contextPath}/cart">Cart</a> |
<a href="${pageContext.request.contextPath}/orders">Orders</a> |

<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders/list">Orders list</a> |
</p>
<p>
<table border="1">
    <tr>
        <td><strong>Order ID</strong></td>
        <td><strong>Action</strong></td>
        <c:forEach var="order" items="${orders}">
    <tr>
        <td><c:out value="${order.id}"/></td>
        <td><form action="${pageContext.request.contextPath}/order/details" method="post">
            <input hidden name = "id" value="${order.id}">
            <input type="submit" value="show order">
        </form></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
</body>
</html>
