<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--导入jstl--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>${requestScope.book.title}图书的信息</title>
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
    <table>
      <tr>
        <th>项目</th>
        <th>内容</th>
      </tr>

      <tr>
        <td>书名</td>
        <td>${book.title}</td>
      </tr>

      <tr>
        <td>作者</td>
        <td>${book.author}</td>
      </tr>

      <tr>
        <td>价格</td>
        <td>${book.price}</td>
      </tr>

      <tr>
        <td>出版日期</td>
        <td>${book.publishingDate}</td>
      </tr>


      <tr>
        <td>销售数量</td>
        <td>${book.saleAmount}</td>
      </tr>


      <tr>
        <td>库存数量</td>
        <td>${book.storeNumber}</td>
      </tr>
    </table>

    <br>
    <br>
    <a href="bookServlet?method=getBooks">返回图书列表</a>


  </center>




  </body>
</html>
