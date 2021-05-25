<%--
  Created by IntelliJ IDEA.
  User: lilin
  Date: 2021/5/24
  Time: 下午4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>Register</title>

    <link rel="stylesheet" type="text/css"  href="style.css">


</head>

<body>
<div class="container">
    <h1>OCR</h1>
    <h2 id="Info">Register</h2>
    <form method="post" action="${pageContext.request.contextPath}/user/addUser">
        <label for="username" class="col-lg-2 control-label">UserName</label>
        <input id="username" type="text" name="username">
        <label for="password" class="col-lg-2 control-label">Password</label>
        <input id="password" type="password" name="password">
        <label for="r_password" class="col-lg-2 control-label">Repeat Password</label>
        <input id="r_password" type="text">

        <input type="submit" value="Register">
    </form>

</div>
<script>

</script>
</body>
</html>