<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin part</title>
</head>
<body>
<p>Admin part</p>
<p>
    <a href="${pageContext.request.contextPath}/products/add">Add product</a> |
    <a href="${pageContext.request.contextPath}/products/edit">Edit product</a> |
    <a href="${pageContext.request.contextPath}/users">Users</a> |
    <a href="${pageContext.request.contextPath}/orders">Orders list</a> |
    <a href="${pageContext.request.contextPath}/">User part</a> |
    <a href="${pageContext.request.contextPath}/logout">Logout</a> |
</p>
</body>
</html>
