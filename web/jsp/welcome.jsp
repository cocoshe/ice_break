<%@ page import="com.coshe.utils.Constants" %>
<%@ page import="com.coshe.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: coshe
  Date: 2021/9/26
  Time: 下午6:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    if (request.getSession().getAttribute(Constants.USER_SESSION)==null){
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        return;
    }
    User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
    response.getWriter().write("当前用户："+user.getName());
%>

<form action="start.do" method="post">
    <button type="submit">
        点击开始测试
    </button>
</form>


</body>
</html>
