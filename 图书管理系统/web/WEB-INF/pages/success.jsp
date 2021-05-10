<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--导入jstl--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>购物成功页面</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">

      $(function () {

          //当单击a标签时，执行以下操作
          $("a").click(function () {
              var serializeVal = $(":hidden").serialize();
              var href = this.href + "&" +serializeVal;
              window.location.href = href;
              return false;
          });
      })

    </script>
  </head>
  <body>
  <%--使用隐藏域来存储表单提交的数据--%>
  <input type="hidden" name="minPrice" value="${param.minPrice}">
  <input type="hidden" name="maxPrice" value="${param.maxPrice}">
  <input type="hidden" name="pageSize" value="${param.pageSize}">
  <input type="hidden" name="pageNo" value="${param.pageNo}">

  <center>
    <br>
    <br>
    <h2>购物成功</h2>

    <form action="userServlet" method="post">
      请输入您的用户名，查询历史交易记录：<br>
      <input type="text" name="username"><br>
      <input type="submit" value="查询">
    </form>

    <br>
    <br>
    <a href="bookServlet?method=getBooks">返回图书列表</a>

  </center>




  </body>
</html>
