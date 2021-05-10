package com.pangsir.bookstore.dao;

import com.pangsir.bookstore.entity.Trade;

import java.util.Set;

public interface TradeDao {

    //向数据表trade添加一条记录
    void insert(Trade trade);

    //根据userId获取相关的Trade集合
    Set<Trade> getTradesWithUserId(Integer userId);
}
