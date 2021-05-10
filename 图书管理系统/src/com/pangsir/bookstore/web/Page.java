package com.pangsir.bookstore.web;


import java.util.List;

/**
 * 通用的封装当前页应包含的所有内容
 */
public class Page<T> {

    private int pageNo = 1;//当前页码
    private int pageSize = 3;//每页显示的记录数
    private long totalItemCount;//总记录数
    private List<T> list;//当前页要显示的记录的集合

    public Page() {
    }

    public Page(int pageNo, int pageSize, long totalItemCount) {

        //当成员变量的值在类的内部发生关联的时候，一定要注意先后顺序
        setPageSize(pageSize);
        setTotalItemCount(totalItemCount);
        //当前页的设置要放在pageSize和totalItemCount的后面，因为它的值依存于前两个变量的存在。
        setPageNo(pageNo);
    }

    //是否有上一页
    public boolean isHasPrev(){
        return getPageNo() > 1 ;
    }
    //是否有下一页
    public boolean isHasNext(){
        return getPageNo() < getTotalPageCount();
    }

    //获取上一页的页码
    public int getPrevPageNo(){
        return isHasPrev()?getPageNo()-1:getPageNo();
    }
    //获取下一页的页码
    public int getNextPageNo(){
        return isHasNext()?getPageNo()+1:getPageNo();
    }


    //自定义获取总页数的方法  100   10  10   105  10   11
    public int getTotalPageCount(){
        return (int) (totalItemCount % pageSize == 0 ? totalItemCount/pageSize :totalItemCount/pageSize +1);
    }


    public int getPageNo() {
        return pageNo;
    }

    //提高pageNo的健壮性
    public void setPageNo(int pageNo) {
        //
        if(pageNo < 1){
            this.pageNo = 1;
        }else if(pageNo > getTotalPageCount()){
            this.pageNo = getTotalPageCount();
        }else {
            this.pageNo = pageNo;
        }
    }
    //
    public int getPageSize() {
        return pageSize;
    }
    //提高pageSize的健壮性
    public void setPageSize(int pageSize) {
        if(pageSize > 0){

            this.pageSize = pageSize;
        }else {
            this.pageSize = 3;
        }
    }

    public long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
