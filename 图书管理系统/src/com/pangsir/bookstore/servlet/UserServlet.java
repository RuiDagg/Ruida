package com.pangsir.bookstore.servlet;

import com.pangsir.bookstore.entity.User;
import com.pangsir.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        username = username.trim();
        //调用业务层根据用户名获取一个User对象，并且该对象包含了交易项的集合，交易项中又包含了交易详情项的集合
        User user = userService.getUserWithTrades(username);

        if (user != null) {
            request.setAttribute("user",user);
            request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");

        }
    }


}
