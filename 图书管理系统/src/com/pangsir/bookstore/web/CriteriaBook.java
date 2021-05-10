package com.pangsir.bookstore.web;

/**
 * 封装了查询条件的类
 */
public class CriteriaBook {

    private float minPrice = 0;//最小价格
    private float maxPrice = Integer.MAX_VALUE;//最高价格
    private int pageNo = 1;//当前页码
    private int pageSize = 3;//每页显示的记录数

    public CriteriaBook() {
    }

    public CriteriaBook(float minPrice, float maxPrice, int pageNo, int pageSize) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
