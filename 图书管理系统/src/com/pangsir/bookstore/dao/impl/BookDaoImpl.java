package com.pangsir.bookstore.dao.impl;

import com.pangsir.bookstore.dao.BookDao;
import com.pangsir.bookstore.entity.Book;
import com.pangsir.bookstore.entity.ShoppingCartItem;
import com.pangsir.bookstore.web.CriteriaBook;
import com.pangsir.bookstore.web.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
 * 创建BookDaoImpl类继承BaseDao类并实现BookDao的接口
 * 目的就是子类可以使用父类写好的通用的方法来实现接口的需求
 * 这样吧开发放到写好sql语句上，使开发更有效率
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public Book getBookById(Integer id) {
        String sql = "select id,author,title,price,publishingDate, salesamount saleAmount,storeNumber,remark " +
                "from mybooks where id = ?";
        return getBean(sql,id);
    }

    @Override
    public List<Book> getPageList(CriteriaBook cb) {
        String sql = "select id,author,title,price,publishingDate, salesamount saleAmount,storeNumber,remark " +
                "from mybooks where price between ? and ? limit ?,?";

        return getBeanList(sql,
                cb.getMinPrice(),
                cb.getMaxPrice(),
                (cb.getPageNo() -1)*cb.getPageSize(),
                cb.getPageSize());
    }

    @Override
    public long getTotalBookCount(CriteriaBook cb) {
        String sql = "select count(*) from mybooks where price between ? and ?";
        return getSingleValue(sql,cb.getMinPrice(),cb.getMaxPrice());
    }

    @Override
    public Page<Book> getPage(CriteriaBook cb) {
        //调用Page类的构造器来创建page对象
        Page<Book> page = new Page<>(cb.getPageNo(),cb.getPageSize(),getTotalBookCount(cb));
        //经过page类处理的条件是安全的可以重新放入查询条件里
        cb.setPageNo(page.getPageNo());
        cb.setPageSize(page.getPageSize());
        //给page对象的list属性赋值，同时getPageList(cb)所用到的查询条件时安全的，是经过过滤的
        page.setList(getPageList(cb));

        return page;
    }

    @Override
    public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql = "update mybooks set salesAmount = salesAmount + ?,storeNumber = storeNumber - ? where id = ?";
        Object[][] params = new Object[items.size()][3];
        List<ShoppingCartItem> scis = new ArrayList<>(items);
        for (int i = 0; i < scis.size(); i++) {
            params[i][0] = scis.get(i).getQuantity();
            params[i][1] = scis.get(i).getQuantity();
            params[i][2] = scis.get(i).getBook().getId();
        }
        batch(sql,params);
    }


}
