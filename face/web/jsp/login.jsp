<%@ page import="com.coshe.utils.Constants" %><%--
  Created by IntelliJ IDEA.
  User: coshe
  Date: 2021/10/4
  Time: 上午1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>



<form action="login.do" method="post">
    用户名 <input type="text" name="username">
    密码<input type="password" name="password">
    <input type="submit"><br>
    ${error}
</form>
</body>
</html>
