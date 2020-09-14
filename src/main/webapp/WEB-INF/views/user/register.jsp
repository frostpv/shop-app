<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <p>User part</p>
    <a href="${pageContext.request.contextPath}/">HomePage</a> |
    <a href="${pageContext.request.contextPath}/products">Products</a> |
    <a href="${pageContext.request.contextPath}/users/registration">Regisration</a> |
    <a href="${pageContext.request.contextPath}/cart">Cart</a> |
    <a href="${pageContext.request.contextPath}/user/orders">Orders</a> |
    <a href="${pageContext.request.contextPath}/admin/index">Admin part</a> |
    <p>
    <p style="color: red">${message}</p>
        <form method="post" action="${pageContext.request.contextPath}/users/registration">
            Please provide your name: <input type="text" name="name"></br>
            Please provide your login: <input type="text" name="login"></br>
            Please provide your password: <input type="password" name="psw"></br>
            Please repeat password: <input type="password" name="psw-repeat"></br>
            <input type="submit" value="Register"></br>
        </form>
    </p>
</body>
</html>
