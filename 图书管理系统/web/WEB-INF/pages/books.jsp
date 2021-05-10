<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--导入jstl--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>分页显示图书的信息</title>
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


          //当id=pageNo的文本输入框的内容发生变化的时候触发该操作
          $("#pageNo").change(function () {

              //1.获取输入的文本
              var value = $(this).val();

              //2.正则表达式的校验
              var reg = /^\d+/g;
              var flag = false;
              var pageNo = 0;
              if(reg.test(value)){
                  //校验通过，进行类型转换
                  pageNo = parseInt(value);
                  if(pageNo >=1 && pageNo <= parseInt("${requestScope.page.totalPageCount}")){
                      flag = true;
                  }
              }

              //3.不符合规范，执行
              if(!flag){
                  alert("您输入的内容不规范，请重新输入");
                  $(this).val("");
                  return;
              }

              //4.满足条件
              var href = "bookServlet?method=getBooks&pageNo="+pageNo+"&"+$(":hidden").serialize();
              window.location.href = href;

          });



      })

    </script>
  </head>
  <body>
  <%--使用隐藏域来存储表单提交的数据--%>
  <input type="hidden" name="minPrice" value="${param.minPrice}">
  <input type="hidden" name="maxPrice" value="${param.maxPrice}">
  <input type="hidden" name="pageSize" value="${param.pageSize}">

  <center>
    <br>
    <br>
     <c:if test="${!empty param.title}">
    您已将${param.title}加入购物车。
       <br><br>
     </c:if>
    <c:if test="${!empty sessionScope.ShoppingCart.books}">
      您的购物车中有${sessionScope.ShoppingCart.bookNumber}本书。<br><br>
      <a href="/bookServlet?method=toCartPage&pageNo=${requestScope.page.pageNo}">查看购物车</a>
    </c:if>

    <br>
    <br>
    <form action="/bookServlet?method=getBooks" method="post">
      价格区间：<input type="text" name="minPrice" size="1">-
      <input type="text" name="maxPrice" size="1"><br>
      每页显示<input type="text" name="pageSize" size="1">条记录<br>
      <input type="submit" value="查询">
    </form>
    <br>

    <br>
    <table cellspacing="10" cellpadding="0">
      <tr>
        <th>书名</th>
        <th>作者</th>
        <th>价格</th>
        <th></th>
      </tr>

      <c:forEach items="${requestScope.page.list}" var="book">
        <tr>
          <td>
            <a href="bookServlet?method=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">
                ${book.title}
            </a>
          </td>
          <td>${book.author}</td>
          <td>${book.price}</td>
          <td>
            <a href="/bookServlet?method=addToCart&id=${book.id}&pageNo=${requestScope.page.pageNo}&title=${book.title}">
              加入购物车
            </a>
          </td>
        </tr>
      </c:forEach>

    </table>
    <br>
    <%--分页条的显示--%>
    共${requestScope.page.totalItemCount}条记录&nbsp;&nbsp;
    共${requestScope.page.totalPageCount}页&nbsp;&nbsp;
    当前第${requestScope.page.pageNo}页&nbsp;&nbsp;
    <c:if test="${requestScope.page.hasPrev}">
      <a href="/bookServlet?method=getBooks&pageNo=1">首页</a>&nbsp;&nbsp;
      <a href="/bookServlet?method=getBooks&pageNo=${requestScope.page.prevPageNo}">上一页</a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${requestScope.page.hasNext}">
      <a href="/bookServlet?method=getBooks&pageNo=${requestScope.page.nextPageNo}">下一页</a>&nbsp;&nbsp;
      <a href="/bookServlet?method=getBooks&pageNo=${requestScope.page.totalPageCount}">末页</a>&nbsp;&nbsp;
    </c:if>
    转到<input type="text" size="1" id="pageNo">页

    <br>



  </center>




  </body>
</html>
