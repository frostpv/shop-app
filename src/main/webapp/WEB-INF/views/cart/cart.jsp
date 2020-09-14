<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Cart</h1>
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
<p>
<table border="1">
    <tr>
        <td><strong>Id</strong></td>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Action</strong></td>
    <c:forEach var="item" items="${cart}">
    <tr>
    <td><c:out value="${item.id}"/></td>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.price}"/></td>
        <td><form action="${pageContext.request.contextPath}/cart/remove" method="post">
            <input hidden name = "id" value="${item.id}">
            <input type="submit" value="delete">
        </form></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
<p>
    <form action="${pageContext.request.contextPath}/order/add" method="post">
        <input hidden name = "userid" value="1">
        <input type="submit" value="Make order">
    </form>
</p>
</body>
</html>
