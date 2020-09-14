<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<p style="color: red">${errorMsg}</p>
<p>
<form method="post" action="${pageContext.request.contextPath}/login">
    Please provide your login: <input type="text" name="login"></br>
    Please provide your password: <input type="password" name="psw"></br>
    <input type="submit" value="Login"></br>
</form>
</p>
</body>
</html>
