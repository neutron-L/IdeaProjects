<%--
  Created by IntelliJ IDEA.
  User: lilin
  Date: 2021/5/24
  Time: 下午4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css"  href="style.css">
</head>

<body>
<div class="container">
    <h1>OCR</h1>
    <h2 id="Info" style="size: A5">Login</h2>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <label for="username" class="col-lg-2 control-label">UserName</label>
        <input id="username" name="username" type="text">
        <label for="password" class="col-lg-2 control-label">Password</label>
        <input id="password" name="password" type="password">

        <input type="submit" value="Login">
    </form>
    <p>No account?</p><a href="${pageContext.request.contextPath}/user/register">Go to register</a>

</div>
<script>

</script>
</body>
</html>