<%@ page import="com.coshe.utils.Constants" %>
<%@ page import="com.coshe.pojo.User" %>
<%@ page import="com.coshe.utils.MybatisUtils" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.coshe.dao.UserMapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: coshe
  Date: 2021/9/25
  Time: 下午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
  <form action="judge.do" method="post">

    <p><img src="${pageContext.request.contextPath}/img/${trueData.pic}" /></p>
    <button type="submit" name="choose" value="${p1.id}">${p1.number} ${p1.position} ${p1.name}</button><br>
    <button type="submit" name="choose" value="${p2.id}">${p2.number} ${p2.position} ${p2.name}</button><br>
    <button type="submit" name="choose" value="${p3.id}">${p3.number} ${p3.position} ${p3.name}</button><br>
    <button type="submit" name="choose" value="${p4.id}">${p4.number} ${p4.position} ${p4.name}</button><br>
    ${trueName}
    <input name="trueDataId" value=${trueData.id} type="hidden">
  </form>

  <form action="upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"><br>
    <input type="submit" value="上传">
  </form>



  <form action="logout.do" method="post">
    <%
      if (request.getSession().getAttribute(Constants.USER_SESSION)==null){
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        return;
      }
      User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      user = mapper.getUserByName(user.getName());

      int show = (int) user.getShow();
      response.getWriter().write("当前用户："+user.getName()+"    当前是否允许自身照片可见：   ["+Constants.STATUS[1 - show]+"]");


      sqlSession.close();
    %>
    ${jsonString}
    <br>
    <input type="submit" value="注销">
  </form>

  <form action="changeShow.do">
    <input type="submit" value="改变自己照片可见状态">
  </form>





</body>
</html>
