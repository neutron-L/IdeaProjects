<%--
  Created by IntelliJ IDEA.
  User: lilin
  Date: 2021/5/24
  Time: 下午2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h3>Register</h3>
<br>
<form method="post" action="${pageContext.request.contextPath}/user/addUser">
  <table>
    <tr>
      <td><label>Username: </label></td>
      <td><input type="text" name="username"> </td>
    </tr>
    <tr>
      <td><label>Password: </label></td>
      <td><input type="text" name="password"> </td>
    </tr>
    <tr>
      <td><label>Repeat Password: </label></td>
      <td><input type="text" name="r_password"> </td>
    </tr>
    <tr>
      <td><input type="submit" value="register"> </td>
    </tr>
  </table>
</form>
</body>
</html>
