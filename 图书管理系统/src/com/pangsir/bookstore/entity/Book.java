package com.pangsir.bookstore.entity;

import java.sql.Date;

/**
 * 封装图书属性的类
 */
public class Book {

    private Integer id;
    private String author;
    private String title;
    private double price;
    private Date publishingDate;
    private int saleAmount;
    private int storeNumber;
    private String remark;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publishingDate=" + publishingDate +
                ", saleAmount=" + saleAmount +
                ", storeNumber=" + storeNumber +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(Integer id, String author, String title, double price, Date publishingDate, int saleAmount, int storeNumber, String remark) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.publishingDate = publishingDate;
        this.saleAmount = saleAmount;
        this.storeNumber = storeNumber;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
