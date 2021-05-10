package com.pangsir.bookstore.test;


import com.pangsir.bookstore.dao.TradeDao;
import com.pangsir.bookstore.dao.impl.TradeDaoImpl;
import com.pangsir.bookstore.entity.Trade;
import org.junit.Test;

import java.sql.Date;
import java.util.Set;

public class TradeDaoImplTest {

    private TradeDao tradeDao = new TradeDaoImpl();
    @Test
    public void insert() {
        Trade trade = new Trade();
        trade.setUserId(1);
        trade.setTradeTime(new Date(new java.util.Date().getTime()));
        tradeDao.insert(trade);
        System.out.println(trade);
    }

    @Test
    public void getTradesWithUserId() {
        Set<Trade> trades = tradeDao.getTradesWithUserId(1);
        for (Trade trade : trades) {
            System.out.println(trade);
        }
    }
}
