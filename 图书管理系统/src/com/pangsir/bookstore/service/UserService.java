package com.pangsir.bookstore.service;

import com.pangsir.bookstore.dao.BookDao;
import com.pangsir.bookstore.dao.TradeDao;
import com.pangsir.bookstore.dao.TradeItemDao;
import com.pangsir.bookstore.dao.UserDao;
import com.pangsir.bookstore.dao.impl.BookDaoImpl;
import com.pangsir.bookstore.dao.impl.TradeDaoImpl;
import com.pangsir.bookstore.dao.impl.TradeItemDaoImpl;
import com.pangsir.bookstore.dao.impl.UserDaoImpl;
import com.pangsir.bookstore.entity.Trade;
import com.pangsir.bookstore.entity.TradeItem;
import com.pangsir.bookstore.entity.User;

import java.util.Iterator;
import java.util.Set;

public class UserService {

    private UserDao userDao = new UserDaoImpl();
    private TradeDao tradeDao = new TradeDaoImpl();
    private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    //获取一个带有交易项集合，并且每个交易项又包含交易详情项集合的User对象
    public User getUserWithTrades(String username) {

        User user = userDao.getUserByUsername(username);

        Integer userId = user.getUserId();

        Set<Trade> trades = tradeDao.getTradesWithUserId(userId);

        Iterator<Trade> iterator = trades.iterator();

        while (iterator.hasNext()){
            Trade trade = iterator.next();
            Integer tradeId = trade.getTradeId();
            Set<TradeItem> tradeItems = tradeItemDao.getTradeItemWithTradeId(tradeId);
            if (tradeItems != null) {
                for (TradeItem tradeItem : tradeItems) {
                    tradeItem.setBook(bookDao.getBookById(tradeItem.getBookId()));
                }
            }
            if (tradeItems !=null && tradeItems.size() != 0){
                trade.setItems(tradeItems);
            }
            if (tradeItems == null && tradeItems.size() == 0){
                iterator.remove();
            }
        }
        if (trades != null && trades.size() != 0) {
            user.setTrades(trades);
        }

        return user;
    }
}
