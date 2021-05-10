package com.pangsir.bookstore.servlet;

import com.google.gson.Gson;
import com.pangsir.bookstore.entity.*;
import com.pangsir.bookstore.service.AccountService;
import com.pangsir.bookstore.service.BookService;
import com.pangsir.bookstore.service.UserService;
import com.pangsir.bookstore.web.BookStoreWebUtils;
import com.pangsir.bookstore.web.CriteriaBook;
import com.pangsir.bookstore.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookService();


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    //用一个servlet响应多个不同的请求
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        //获取参数，得到链接传递过来的参数值
        String methodName = request.getParameter("method");

        //根据方法名去调用当前类中对应的方法（反射的知识）

        try {
            Method method = this.getClass().getDeclaredMethod(
                    methodName,
                    HttpServletRequest.class,
                    HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //结账操作
    protected void cash(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String accountIdStr = request.getParameter("accountId");


        //通过四个校验
        StringBuffer errors = validateFormField(username,accountIdStr);
        if(errors.toString().equals("")){
            //非空验证通过
            errors = validateUsernameMatchAccountId(username,accountIdStr);
            if(errors.toString().equals("")){
                //用户名和账号是否匹配的验证通过
                errors = validateBookStoreNumber(request);
                if(errors.toString().equals("")){
                    //库存是否充足的验证通过
                    errors = validateBalance(request,accountIdStr);
                }

            }

        }
        //未通过校验，返回原页面，显示错误信息，重新填写表单
        if(!errors.toString().equals("")){
            request.setAttribute("errors",errors);
            //请求转发到付款成功页面
            request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request,response);
            //结束下面操作
            return;
        }

        //具体调用service层去进行结账操作
        bookService.cash(request,username,accountIdStr);


        //请求转发到付款成功页面
        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
    }



    //非空验证
    private StringBuffer validateFormField(String username,String accountId){
        StringBuffer error = new StringBuffer("");
        if(username == null || username.trim().equals("")){
            error.append("用户名不能为空<br>");
        }
        if(accountId == null || accountId.trim().equals("")){
            error.append("账号不能为空");
        }
        return error;
    }

    private UserService userService = new UserService();

    //用户名和账户是否匹配
    private StringBuffer validateUsernameMatchAccountId(String username,String accountId){
        StringBuffer error = new StringBuffer("");
        User user = userService.getUserByUsername(username);
        if (user != null) {
            Integer userAccountId = user.getAccountId();
            if (userAccountId != Integer.parseInt(accountId)) {
                error.append("用户名和账号不匹配");
            }

        }else {
            error.append("该用户不存在");
        }

        return error;
    }

    //库存是否充足(购物车中的某个商品的数量，大于该商品的库存数量)
    private StringBuffer validateBookStoreNumber(HttpServletRequest request){
        StringBuffer error = new StringBuffer("");
        ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
        for (ShoppingCartItem sci : shoppingCart.getItems()) {
            //购物车中打算购买某商品的数量
            int quantity = sci.getQuantity();
            int storeNumber = bookService.getBookById(sci.getBook().getId()).getStoreNumber();
            if (quantity > storeNumber) {
                error.append(sci.getBook().getTitle()+"的库存不足<br>");
            }
        }
        return error;
    }

    private AccountService accountService = new AccountService();

    //账户余额是否充足
    private StringBuffer validateBalance(HttpServletRequest request,String accountId){
        StringBuffer error = new StringBuffer("");
        ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
        double totalMoney = shoppingCart.getTotalMoney();

        Account account = accountService.getAccountById(Integer.parseInt(accountId));

        if (account != null){
            if(totalMoney > account.getBalance()){
                error.append("账户余额不足");
            }
        }else {
            error.append("该账户不存在");
        }

        return error;
    }



    //请求转发到cash.jsp页面
    protected void toCashPage(HttpServletRequest request,
                                      HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request,response);
    }

    //修改单个购物项的数量，返回JSON数据
    protected void updateItemQuantity(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {
        //获取参数
        String idStr = request.getParameter("id");
        String quantityStr = request.getParameter("quantity");
        //类型转换
        int id = -1;
        int quantity = -1;

        try {
            id = Integer.parseInt(idStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
        }
        //获取购物车对象
        ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
        //调用service的方法来更新购物项的数量属性
        if(id > 0 && quantity > 0){
            bookService.updateItemQuantity(sc,id,quantity);
        }
        //返回JSON数据
        Map<String,Object> result = new HashMap<>();
        result.put("bookNumber",sc.getBookNumber());
        result.put("totalMoney",sc.getTotalMoney());
        //将map转换为json字符串
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        System.out.println(jsonStr);
        //将json字符串响应给浏览器
        response.getWriter().write(jsonStr);
    }

    //删除购物项
    protected void removeItem(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {
        //接受参数
        String idStr = request.getParameter("id");
        //类型转换
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
        }
        if (id > 0){
            //获取购物车
            ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
            //删除购物项
            bookService.removeItemFromShoppingCart(sc,id);
            if (sc.isEmpty()) {
                //若购物车为空，则转发到空购物车页面
                request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request,response);
            }else {
                //若购物车不为空，则转发到当前购物车页面
                request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);
            }

        }else {
            //若id不存在，则重定向到错误页面
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
        }
    }

    //清空购物车
    protected void clearCart(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {
        //获取购物车对象
        ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
        //调用service层的方法清空购物车
        bookService.clearCart(shoppingCart);
        //请求转发到emptycart.jsp页面
        request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request,response);

    }

    //查看购物车
    protected void toCartPage(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        //请求转发到cart.jsp页面
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);

    }

    //将商品加入购物车
    protected void addToCart(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        //1.接受参数
        String idStr = request.getParameter("id");
        //2.类型转换
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {}
        //3.获取购物车
        ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);

        Book book =null;
        if (id > 0){
            //4.调用service方法，获取图书对象
            book = bookService.getBookById(id);
            //5.加入购物车
            sc.addBookToCart(book);
            //6.成功，回到books.jsp页面
            getBooks(request,response);
        }else {
            //失败，进入error-1.jsp
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
        }

    }

    //查询单个商品信息，并转发到book.jsp页面上去显示
    protected void getBook(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取参数
        String idStr = request.getParameter("id");
        //2.类型转换
        int id = -1;
        Book book = null;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
        }
        //3.调用service层方法获取单个图书的对象
        if (id > 0){
            book = bookService.getBookById(id);
        }
        //4.存入请求域
        request.setAttribute("book",book);

        if (book != null) {
            //5.请求转发到book.jsp页面
            request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request,response);
        }else {
            //6.重定向到错误页面
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
        }

    }

    //查询所有的商品分页信息，并转发到books.jsp页面上去显示
    protected void getBooks(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取参数
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        //2.类型的转换
        int pageNo = 1;
        int pageSize = 3;
        float minPrice = 0;
        float maxPrice = Integer.MAX_VALUE;

        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {}
        try {
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) { }

        try {
            minPrice = Integer.parseInt(minPriceStr);
        } catch (NumberFormatException e) { }

        try {
            maxPrice = Integer.parseInt(maxPriceStr);
        } catch (NumberFormatException e) { }

        //3.封装成查询条件
        CriteriaBook cb = new CriteriaBook(minPrice,maxPrice,pageNo,pageSize);
        //4.调用业务层对象去获取Page<Book>对象
        Page<Book> page = bookService.getBooks(cb);
        //将page对象存入请求域中
        request.setAttribute("page",page);
        //请求转发到books.jsp页面
        request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request,response);

    }


}
