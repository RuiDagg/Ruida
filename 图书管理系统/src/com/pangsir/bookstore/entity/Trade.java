package com.pangsir.bookstore.entity;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 交易记录类
 */
public class Trade {

    private Integer tradeId;
    private Integer userId;
    private Date tradeTime;

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", userId=" + userId +
                ", tradeTime=" + tradeTime +
                '}';
    }

    public Trade() {
    }

    public Trade(Integer tradeId, Integer userId, Date tradeTime) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.tradeTime = tradeTime;
    }

    //一笔交易关联多个TradeItem对象
    private Set<TradeItem> items = new LinkedHashSet<>();

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Set<TradeItem> getItems() {
        return items;
    }

    public void setItems(Set<TradeItem> items) {
        this.items = items;
    }
}
