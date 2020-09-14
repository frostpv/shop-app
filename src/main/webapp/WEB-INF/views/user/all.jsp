<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<h1>All users</h1>
<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders/list">Orders list</a> |
    <a href="${pageContext.request.contextPath}/">User part</a> |
</p>
<p>
<table border="1">
    <tr>
        <td><strong>ID</strong></td>
        <td><strong>User</strong></td>
        <td><strong>Action</strong></td>
        <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><form action="${pageContext.request.contextPath}/user/remove" method="post">
            <input hidden name = "id" value="${user.id}">
            <input type="submit" value="delete">
        </form></td>
    </tr>
    </c:forEach>
    </tr>
</table>
</p>
</body>
</html>
