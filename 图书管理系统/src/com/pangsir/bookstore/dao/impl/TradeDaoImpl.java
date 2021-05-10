package com.pangsir.bookstore.dao.impl;

import com.pangsir.bookstore.dao.TradeDao;
import com.pangsir.bookstore.entity.Trade;

import java.util.LinkedHashSet;
import java.util.Set;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {


    @Override
    public void insert(Trade trade) {
        String sql = "insert into trade (userid,tradetime) values(?,?)";
        //插入记录并返回自动生成的主键值
        long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
        //将生成的主键值赋值给为trade对象的tradeId属性
        trade.setTradeId((int) tradeId);

    }

    @Override
    public Set<Trade> getTradesWithUserId(Integer userId) {
        String sql = "select tradeId,userId,tradeTime from trade where userid = ? order by tradeTime DESC";

        return new LinkedHashSet<>(getBeanList(sql,userId));
    }
}
