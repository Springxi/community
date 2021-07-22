package com.project.community.entity;

/**
 * 封装分页相关的信息.
 */
public class Page {
    // 当前页码
    private int current =1 ;
    // 显示上限
    private int limit =10;
    // 数据总数(用于计算总页数)
    private int rows;
    // 查询路径(用于复用分页链接)
    private String path;


    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     */
    public int getOffset(){
        // current * limit - limit
        return  (current-1) * limit;
    }

    /**
     * 获取总页数
     */
    public int getTotal(){
        // rows/limit [+1]
        return (rows %limit ==0) ? rows/limit : rows/limit +1;
    }

    /**
     * 获取起始页码
     */
    public int getFrom (){
        return  current >2 ? current-2 : 1;
    }

    /**
     * 获取结束页码
     */

    public int getTo (){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
