<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>JavaWeb案例的学习</title>
  </head>
  <body>
  <h1>网上书城</h1>
  <a href="${pageContext.request.contextPath}\bookServlet?method=getBooks">分页查看图书信息</a>
  </body>
</html>
