package com.pangsir.bookstore.dao.impl;

import com.pangsir.bookstore.dao.TradeItemDao;
import com.pangsir.bookstore.entity.TradeItem;

import java.util.*;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql = "insert into tradeitem(bookid,quantity,tradeid) values(?,?,?)";
        Object[][] params = new Object[items.size()][3];
        List<TradeItem> tradeItems = new ArrayList<>(items);
        for (int i = 0; i < tradeItems.size(); i++) {
            params[i][0] = tradeItems.get(i).getBookId();
            params[i][1] = tradeItems.get(i).getQuantity();
            params[i][2] = tradeItems.get(i).getTradeId();
        }
        batch(sql,params);
    }

    @Override
    public Set<TradeItem> getTradeItemWithTradeId(Integer tradeId) {
        String sql = "select itemid tradeItemId,bookId,quantity,tradeId from tradeitem where tradeid = ?";
        return new LinkedHashSet<>(getBeanList(sql,tradeId));
    }
}
