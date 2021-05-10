package com.pangsir.bookstore.web;


import com.pangsir.bookstore.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 获取购物车对象的工具类
 */
public class BookStoreWebUtils {

    /**
     * 从session中获取购物车对象
     * 若有，则正常返回
     * 若没有，则创建购物车对象，并存入session
     */
    public static ShoppingCart getShoppingCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
        if (sc == null) {
           sc = new ShoppingCart();
           session.setAttribute("ShoppingCart",sc);
        }
        return sc;
    }
}
