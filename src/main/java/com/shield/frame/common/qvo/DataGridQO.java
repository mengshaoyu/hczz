package com.shield.frame.common.qvo;

/**
 * easyUI对应的分页请求QO
 */
public class DataGridQO {

    /**
     * 当前页,名字必须为page
     */
    private int page;
    /**
     * 每页大小,名字必须为rows
     */
    private int rows;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序规则
     */
    private String order;
    /**
     * 开始记录数
     */
    private int recordStart;
    /**
     * 终止记录数
     */
    private int recordEnd;
    /**
     * 总记录数
     */
    private int totalRecord;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(int recordStart) {
        this.recordStart = recordStart;
    }

    public int getRecordEnd() {
        return recordEnd;
    }

    public void setRecordEnd(int recordEnd) {
        this.recordEnd = recordEnd;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {

        this.totalRecord = totalRecord;

        if (Math.ceil(totalRecord / rows) < page) {
            this.setPage((int) Math.ceil(totalRecord / 1.0 / rows));
        }
        if (this.getPage() == 0) {
            this.setPage(1);
        }

        this.recordStart = page > 0 ? (page - 1) * rows : 0;

        this.recordEnd = recordStart + rows;
    }
}
