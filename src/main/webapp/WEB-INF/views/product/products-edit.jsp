<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders">Orders list</a> |
    <a href="${pageContext.request.contextPath}/">User part</a> |
    <a href="${pageContext.request.contextPath}/logout">Logout</a> |
</p>
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
        <td><form action="${pageContext.request.contextPath}/products/delete" method="post">
            <input hidden name = "id" value="${product.id}">
            <input type="submit" value="delete">
        </form>
       </td>
    </tr>
    </c:forEach>
    </tr>
    <tr>
        <form method="post" action="${pageContext.request.contextPath}/products/add">
        <td></td>
        <td><input type="text" pattern=".{1,}" required  name="name" placeholder="product name"></td>
        <td><input type="number" name="price" value="0"></td>
        <td><input type="submit" value="Add"></td>
        </form>
    </tr>
</table>
</p>
</body>
</html>
