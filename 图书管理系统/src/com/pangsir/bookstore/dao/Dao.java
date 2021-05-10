package com.pangsir.bookstore.dao;

import java.util.List;

public interface Dao<T> {

    //1.添加一条记录，并返回自动生成的主键
    long insert(String sql,Object... args);
    //2.正常的增、删、改的更新操作
    void update(String sql,Object... args);
    //3.查询一条记录并转换为一个对象的方法
    T getBean(String sql,Object... args);
    //4.查询多条记录并转换为一个对象集合的方法
    List<T> getBeanList(String sql, Object... args);
    //5.获取单个值的方法，比如统计人数，商品数量
    <E> E getSingleValue(String sql, Object... args);
    //6.批量的执行增、删、改的更新操作
    void batch(String sql,Object[]... params);
}
