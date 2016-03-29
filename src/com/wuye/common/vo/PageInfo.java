package com.wuye.common.vo;

/**
 * =============================================================================<br>
 * 版权： 福富软件 版权所有 (c) 2007 - 2008<br>
 * 文件包： com.ffcs.crm.common.vo 所含类： PageInfo 编写人员：陈军 创建日期：2007-03-27 功能说明:
 * 保存分页信息返回值 更新记录： 日期 作者 内容<br>
 * =============================================================================<br>
 * 2007-03-29 陈军 创建新文件，并实现基本功能
 * <p/>
 * =============================================================================<br>
 */
public class PageInfo {

    private int perPageCount; // 每页记录数

    private int totalPageCount; // 总页数

    private int currentPage; // 当前页

    private int totalCount; // 总记录数

    private java.util.List dataList; // 查询出的记录集合

    private RetVO retVo; // 返回的信息

    /**
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo() {
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return List
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public java.util.List getDataList() {
        return dataList;
    }

    /**
     * @param dataList List
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setDataList(java.util.List dataList) {
        this.dataList = dataList;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getPerPageCount() {
        return perPageCount;
    }

    /**
     * @param perPageCount int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setPerPageCount(int perPageCount) {
        this.perPageCount = perPageCount;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @param totalPageCount int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * @return RetVO
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public RetVO getRetVo() {
        return retVo;
    }

    /**
     * @param retVo RetVO
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setRetVo(RetVO retVo) {
        this.retVo = retVo;
    }

}