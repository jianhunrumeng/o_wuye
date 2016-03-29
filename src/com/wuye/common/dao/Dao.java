package com.wuye.common.dao;

import com.wuye.common.exception.ManagerException;
import com.wuye.common.vo.PageInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.dao.Dao.java
 * @所含类：Dao
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-13
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-13 wuq 创建
 * <p/>
 * =============================================================<br>
 */
public interface Dao {
    /**
     * @param clazz 参数类
     * @param obj   参数对象
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List getObjects(Class clazz, Object obj);

    /**
     * @param clazz 参数类
     * @param id    参数
     * @return Object
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Object getObject(Class clazz, Serializable id);

    /**
     * @param o 参数类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void saveObject(Object o);

    /**
     * @param o 参数类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void addObject(Object o);

    /**
     * @param o 参数类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void merge(Object o);

    /**
     * @param o 参数类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-28 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */

    public void updateObject(Object o);

    /**
     * Generic method to delete an object based on class and id
     *
     * @param clazz model class to lookup
     * @param id    the identifier (primary key) of the class
     */
    public void removeObject(Class clazz, Serializable id);

    /**
     * @param string 查询语句
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-28 tanyw 创建方法，并实现其功能 根据HQL语句查询
     * ==============================================================<br>
     */

    public List findByHql(String string);

    /**
     * @param sql         查询语句
     * @param params      查询参数
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能 根据SQL语句分页查找PO对象数据
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByHQLAndParams(final String sql,
                                               final List params, final int currentPage, final int perPageNum);

    /**
     * @param sql         查询语句
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-4-4 tanyw 创建方法，并实现其功能 分页查询
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByHQL(final String sql, final int currentPage,
                                      final int perPageNum);

    /**
     * @param entities 数据集记录
     * @author: liujr
     * @修改记录： ==============================================================<br>
     * 日期: 2007-4-11 liujr 创建方法，并实现其功能 从数据库删除指定数据集的记录
     * ==============================================================<br>
     */
    public void deleteAll(Collection entities);

    /**
     * @param sql 查询语句
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-8 tanyw 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     * ==============================================================<br>
     */

    public List findListByJDBCSQL(final String sql);

    /**
     * 获取单一值
     *
     * @param sql
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     * 日期:2008-6-12      zfz         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByJDBCSQL(final String sql);

    /**
     * 获取单一值
     *
     * @param sql
     * @return
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2009-1-5      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByJDBCAndParamsSQL(final String sql, final List params);

    public String getSingleValueByHql(final String hql);

    /**
     * @param sql         查询语句
     * @param params      查询参数
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @param isGroupby   是否分组
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-8 tanyw 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby);

    /**
     * @param sql         查询语句
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @param isGroupby   是否分组
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-8 tanyw 创建方法，并实现其功能 根据JDBCSQL语句查询数据
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQL(final String sql,
                                          final int currentPage, final int perPageNum, final boolean isGroupby);

    /**
     * @param seqName 参数
     * @return String
     * @author: panchh
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-21 panchh 创建方法，并实现其功能 取SEQ值
     * ==============================================================<br>
     */
    public String getSeqNextval(String seqName);

    /**
     * @param sql    HQL语句
     * @param params 参数
     * @return List
     * @author: liujr
     * @修改记录： ==============================================================<br>
     * 日期:2007-10-29 liujr 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByHQLAndParams(final String sql, final List params);

    /**
     * @param sql    jdbcsql
     * @param params 参数
     * @return List
     * @author: liujr
     * @修改记录： ==============================================================<br>
     * 日期:2007-10-29 liujr 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByJDBCSQLAndParams(final String sql, final List params);

    /**
     * 获取字段comment
     *
     * @param owner     表空间所有者
     * @param tableName 表名
     * @param colName   列名
     * @return 字段中文名
     * @throws ManagerException ManagerException
     * @author: panchh
     * @修改记录： ==============================================================<br>
     * 日期:Dec 3, 2007 panchh 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getColumnChineseName(String owner, String tableName,
                                       String colName) throws ManagerException;

    /**
     * 获取单一值HQL,绑定变量
     *
     * @param hql
     * @param params
     * @return
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2008-12-31 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByHqlAndParams(String hql, List params);

    /**
     * @param sql
     * @param countSql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @return
     * @author: newlock
     * @修改记录： ==============================================================<br>
     * 日期:2010-6-23      newlock         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql, final String countSql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby);

    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @return
     * @author: liuxl
     * @修改记录： ==============================================================<br>
     * 日期:2011-3-14      liuxl         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum);

    /**
     * 查询最大数量
     *
     * @param sql
     * @param params
     * @param maxCount
     * @return
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:Feb 18, 2012      tanyw         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByJDBCSQLAndParams(final String sql, final List params,
                                           final int maxCount);

    public List findListByHQLAndParams(final String sql, final List params,
                                       final int maxCount);

    public Object qryZbInfo(String sql, List params, Class cl);

    public void updateBySql(String seq, List params);

    public void executeSql(String sql, List param);

    public void updateBySqlForInvoice(final String sql, final List params);

    /**
     * Set the index of the first object that will be retrieved by
     * {@link #list} and {@link #list(Filter)} when value 0 has been
     * set for 'first' in the filter.<p>
     * The value in the filter, if different than 0, will always superseed
     * this value.
     *
     * @param first the index of the first object that will be retrieved by
     *              {@link #list} e {@link #list(Filter)}
     */
    void setFirst(int first);

    /**
     * Retrieves the index of the first object that will be retrieved by
     * {@link #list} e {@link #list(Filter)}.
     *
     * @return the index of the first object that will be retrieved by
     * {@link #list} e {@link #list(Filter)}
     */
    int getFirst();

    /**
     * Set the index of the last object that will be retrieved by
     * {@link #list} e {@link #list(Filter)} when value 0 has been
     * set for 'first' in the filter.<p>
     * The value in the filter, if different than 0, will always superseed
     * this value.<p>
     * If the repository contains less objects than the indicated index,
     * all the objects will be retrieved.
     *
     * @param last the index of the last object that will be retrieved by
     *             {@link #list} and {@link #list(Specification)}
     */
    void setLast(int last);

    /**
     * Retrieves the index of the last object that will be retrieved by
     * {@link #list} e {@link #list(Specification)}.
     * This method will retrieve the same number that has been set using
     * {@link #setLast(int)} independently of the repository content size.
     *
     * @return the index of the last object that will be retrieved by
     * {@link #list} and {@link #list(Filter)}
     */
    int getLast();

    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby, final Class zz);

    /**
     * 通过JDBC执行SQL查询语句，获取分页信息。
     *
     * @param <E>         实体类型
     * @param sql         SQL语句
     * @param elementType 元素类型
     * @param params      参数列表
     * @param currentPage 当前页码
     * @param perPageNum  每页记录数
     * @return 分页信息
     */
    <E> PageInfo jdbcFindPageInfo(String sql, Class<E> elementType, List<Object> params,
                                  int currentPage, int perPageNum);
}
