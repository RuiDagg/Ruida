package com.pangsir.bookstore.entity;

/**
 * 交易详情项
 */
public class TradeItem {

    private Integer tradeItemId;
    private Integer bookId;
    private int quantity;
    private Integer tradeId;

    private Book book;

    @Override
    public String toString() {
        return "TradeItem{" +
                "tradeItemId=" + tradeItemId +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                ", tradeId=" + tradeId +
                '}';
    }

    public TradeItem() {
    }

    public TradeItem(Integer tradeItemId, Integer bookId, int quantity, Integer tradeId) {
        this.tradeItemId = tradeItemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.tradeId = tradeId;
    }

    public Integer getTradeItemId() {
        return tradeItemId;
    }

    public void setTradeItemId(Integer tradeItemId) {
        this.tradeItemId = tradeItemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
