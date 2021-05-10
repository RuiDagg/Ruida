package com.pangsir.bookstore.test;


import com.pangsir.bookstore.dao.TradeItemDao;
import com.pangsir.bookstore.dao.impl.TradeItemDaoImpl;
import com.pangsir.bookstore.entity.TradeItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TradeItemDaoImplTest {

    private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
    @Test
    public void batchSave() {
        Collection<TradeItem> items = new ArrayList<>();
        items.add(new TradeItem(null,1,10,17));
        items.add(new TradeItem(null,2,20,17));
        items.add(new TradeItem(null,3,30,17));
        items.add(new TradeItem(null,4,40,17));
        items.add(new TradeItem(null,5,50,17));
        tradeItemDao.batchSave(items);
    }

    @Test
    public void getTradeItemWithTradeId() {
    }
}
