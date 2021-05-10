package com.pangsir.bookstore.entity;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 购物车
 */
public class ShoppingCart {
    //购物车由购物项组成，为方便操作，用购物项中商品的id为key,用购物项本身为value组成一个map集合
    Map<Integer,ShoppingCartItem> books = new HashMap<>();


    //获取购物车中购物项集合的方法
    public Collection<ShoppingCartItem> getItems(){
        return books.values();
    }

    //添加商品进购物车
    public void addBookToCart(Book book){
        Integer bookId = book.getId();
        //先检查购物车中是否有该商品
        ShoppingCartItem shoppingCartItem = books.get(bookId);
        if (shoppingCartItem != null) {
            //若有，则商品数量加1
            shoppingCartItem.increment();
        }else {
            //若没有，则创建新的购物项，并加入购物车的map集合中
            shoppingCartItem = new ShoppingCartItem(book);
            books.put(bookId,shoppingCartItem);
        }
    }
    //检查购物车中是否有指定id的商品
    public boolean hasBook(Integer id){
        return books.containsKey(id);
    }

    //获取购物车中商品总数量的方法
    public int getBookNumber(){
        int total = 0;
        for (ShoppingCartItem item : getItems()) {
            total += item.getQuantity();
        }
        return total;
    }

    //获取购物车的总金额
    public double getTotalMoney(){
        double total = 0;
        for (ShoppingCartItem item : getItems()) {
            total += item.getItemMoney();
        }
        return total;
    }

    //购物车是否为空
    public boolean isEmpty(){
        return books.isEmpty();
    }
    //根据id移除某个购物项
    public void removeItemById(Integer id){
        books.remove(id);
    }
    //根据id获取某个购物项的方法
    public ShoppingCartItem getItem(Integer id){
        return books.get(id);
    }

    //更新（修改）指定id的购物项的商品数量
    public void updateItemQuantity(Integer id,int quantity){
        ShoppingCartItem shoppingCartItem = getItem(id);
        if (shoppingCartItem != null) {
            shoppingCartItem.setQuantity(quantity);
        }

    }

    public ShoppingCart() {
    }

    public ShoppingCart(Map<Integer, ShoppingCartItem> books) {
        this.books = books;
    }

    //获取购物车中map集合对象
    public Map<Integer, ShoppingCartItem> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, ShoppingCartItem> books) {
        this.books = books;
    }
}
