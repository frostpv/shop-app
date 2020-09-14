<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order admin list information</title>
</head>
<body>
<h1>Order admin list information</h1>
<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders/list">Orders list</a> |
    <a href="${pageContext.request.contextPath}/">User Part</a> |
</p>
</body>
<p>
<table border="1">
    <tr>
        <td><strong>Order ID</strong></td>
        <td><strong>User ID</strong></td>
        <td><strong>Action</strong></td>
        <c:forEach var="order" items="${orders}">
    <tr>
        <td><c:out value="${order.id}"/></td>
        <td><c:out value="${order.idUser}"/></td>
        <td>
            <form action="${pageContext.request.contextPath}/order/details" method="post">
            <input hidden name = "id" value="${order.id}">
            <input type="submit" value="show order">
        </form>
            <form action="${pageContext.request.contextPath}/order/delete" method="post">
                <input hidden name = "id" value="${order.id}">
                <input type="submit" value="delete order">
            </form></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
</body>
</html>
