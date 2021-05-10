<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--导入jstl--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>付款页面</title>
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
    您一共购买了${sessionScope.ShoppingCart.bookNumber}本书
    <br>
    <br>
    应付：￥${sessionScope.ShoppingCart.totalMoney}
    <br>
    <c:if test="${!empty requestScope.errors}">
        <font color="red">${requestScope.errors}</font>
    </c:if>
    <br>
    <form action="bookServlet?method=cash" method="post">
      <table>
        <tr>
          <td>用户姓名</td>
          <td><input type="text" name="username"></td>
        </tr>
        <tr>
          <td>信用卡账号</td>
          <td><input type="text" name="accountId"></td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="submit" value="付款">
          </td>
        </tr>
      </table>

    </form>

    <a href="bookServlet?method=getBooks">返回图书列表</a>


  </center>




  </body>
</html>
