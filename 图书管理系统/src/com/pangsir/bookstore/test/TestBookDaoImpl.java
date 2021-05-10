package com.pangsir.bookstore.test;

import com.pangsir.bookstore.dao.BookDao;
import com.pangsir.bookstore.dao.impl.BookDaoImpl;
import com.pangsir.bookstore.entity.Book;
import com.pangsir.bookstore.entity.ShoppingCartItem;
import com.pangsir.bookstore.web.CriteriaBook;
import com.pangsir.bookstore.web.Page;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestBookDaoImpl {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void getBookById(){
        Book book = bookDao.getBookById(8);
        System.out.println(book);

    }

    @Test
    public void getPageList(){
        CriteriaBook cb = new CriteriaBook(50,70,2,3);
        List<Book> books = bookDao.getPageList(cb);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public  void getPage(){
        CriteriaBook cb = new CriteriaBook(50,70,-5,5);
        Page<Book> page = bookDao.getPage(cb);
        System.out.println("当前第几页"+page.getPageNo());
        System.out.println("每页显示多少条记录"+page.getPageSize());
        System.out.println("共多少页"+page.getTotalPageCount());
        System.out.println("共多少条记录"+page.getTotalItemCount());

        List<Book> books = page.getList();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void getTotalBookCount(){
        CriteriaBook cb = new CriteriaBook(50,70,-5,5);
        long totalBookCount = bookDao.getTotalBookCount(cb);
        System.out.println(totalBookCount);
    }

    @Test
    public void batchUpdateStoreNumberAndSalesAmount(){
        Collection<ShoppingCartItem> items = new ArrayList<>();
        items.add(new ShoppingCartItem(bookDao.getBookById(1),10));
        items.add(new ShoppingCartItem(bookDao.getBookById(2),20));
        items.add(new ShoppingCartItem(bookDao.getBookById(3),30));
        items.add(new ShoppingCartItem(bookDao.getBookById(4),40));
        bookDao.batchUpdateStoreNumberAndSalesAmount(items);
    }

}
