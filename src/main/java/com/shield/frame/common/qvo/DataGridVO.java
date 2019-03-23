package com.shield.frame.common.qvo;

import java.util.List;

/**
 * easyUI对应的分页请求VO
 */
public class DataGridVO<T> extends BaseVO {

    /**
     * 总条数
     */
    private long total;

    /**
     * 一览分页信息
     */
    private List<T> rows;

    /**
     * 当前页
     */
    private int currentPage;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
