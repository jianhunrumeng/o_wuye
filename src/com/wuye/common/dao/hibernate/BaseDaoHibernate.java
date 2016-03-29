package com.wuye.common.dao.hibernate;

import com.wuye.common.dao.Dao;
import com.wuye.common.exception.ManagerException;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.date.DateUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.dao.hibernate.BaseDaoHibernate.java
 * @所含类：BaseDaoHibernate
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
@Repository(value = "baseDaoHibernate")
public class BaseDaoHibernate extends HibernateDaoSupport implements Dao {
    /**
     * log Log
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * 强制分页开关,-1为不控制
     */
    public static int forcePageNum = -1;

    /**
     * 分页的最大条数，如果传的参数超过该值，则强制设为此值，-1为不控制
     */
    public static int forceMaxPageNum = 1000;
    /**
     * JdbcTemplate对象。
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    /**
     * 设置第一条数据编号。
     */
    private int first;
    /**
     * 设置最后一条数据编号。
     */
    private int last;

    public int getFirst() {
        return this.first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return this.last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    /**
     * 获取JdbcTemplate对象。
     *
     * @return JdbcTemplate对象
     */
    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = SpringUtil.getBean("jdbcTemplate");
        }
        return this.jdbcTemplate;
    }

    /**
     * @param o Object
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void saveObject(Object o) {
        Date nowDate = DateUtil.getNowDate();
        try {
            BeanUtils.copyProperty(o, "updateDate", nowDate);
        } catch (Exception e) {

        }
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     * @param o Object
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void addObject(Object o) {
        Date nowDate = DateUtil.getNowDate();
        try {
            BeanUtils.copyProperty(o, "updateDate", nowDate);
        } catch (Exception e) {

        }
        getHibernateTemplate().save(o);
    }

    /**
     * @param o 类对象
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void updateObject(Object o) {
        Date nowDate = DateUtil.getNowDate();
        try {
            BeanUtils.copyProperty(o, "updateDate", nowDate);
        } catch (Exception e) {

        }
        getHibernateTemplate().update(o);
    }

    /**
     * @param clazz 参数类
     * @param id    参数类
     * @return Object
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Object getObject(Class clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);

        /*if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }*/

        return o;
    }

    /**
     * @param clazz 参数类
     * @param obj   类对象
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List getObjects(Class clazz, final Object obj) {

        // return getHibernateTemplate().loadAll(clazz);
        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API.
         */

        if (obj == null) {
            return getHibernateTemplate().loadAll(clazz);
            // return getHibernateTemplate().find("from "+clazz.getName());
        } else {
            // filter on properties set in the saleTask
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException {
                    Example ex = Example.create(obj).excludeNone()
                            .excludeZeroes().ignoreCase().enableLike(
                                    MatchMode.ANYWHERE);
                    return session.createCriteria(obj.getClass()).add(ex)
                            .list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }

    }

    /**
     * @param clazz 参数类
     * @param id    参数类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-13 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }

    /**
     * @param hql 查询语句
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-4-13 tanyw 创建方法，并实现其功能 根据HQL语句查询对象集合
     * ==============================================================<br>
     */
    public List findByHql(String hql) {
        if (BaseDaoHibernate.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return getHibernateTemplate().find(hql);
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return findFirstPageInfoByHQLAndParams(hql, null, 1, BaseDaoHibernate.forcePageNum)
                    .getDataList();
        }
    }

    /**
     * 根据hibernate的Hql语句和参数查询出分页信息
     *
     * @param sql         查询语句
     * @param params      查询参数
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-29 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByHQLAndParams(final String sql,
                                               final List params, final int currentPage, final int perPageNum) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;

                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }

                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");

                        String totalCountSql = "select count(*) "
                                + sqlStr.substring(sqlStr.indexOf("FROM "));

                        Query totalQuery = session.createQuery(totalCountSql);
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    totalQuery.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    totalQuery.setString(k, params.get(k)
                                            .toString());
                                }
                                if (param instanceof Long) {
                                    totalQuery.setLong(k,
                                            ((Long) params.get(k)).longValue());
                                }
                                if (param instanceof Date) {
                                    totalQuery.setDate(k,
                                            ((Date) params.get(k)));
                                }
                            }
                        }

                        List countList = totalQuery.list();

                        // 获取总记录数
                        int totalCounts = 0;

                        // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                        if (sqlStr.indexOf("Group by") == -1
                                && sqlStr.indexOf("group by") == -1
                                && sqlStr.indexOf("Group By") == -1) {
                            totalCounts = ((Long) countList.get(0)).intValue();
                        } else {
                            totalCounts = list.size();
                        }

                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }
                        // 计算总页数
                        int totalPages = totalCounts / perPageSize;
                        totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                                : totalPages;

                        Query query = session.createQuery(sqlStr);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

                        pageInfo.setTotalCount(totalCounts);
                        pageInfo.setTotalPageCount(totalPages);
                        pageInfo.setCurrentPage(currentPageNum);
                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * @param sql         查询语句
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-29 tanyw 创建方法， 并实现其功能 根据HQL语句分页查询
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByHQL(final String sql, final int currentPage,
                                      final int perPageNum) {
        return findPageInfoByHQLAndParams(sql, null, currentPage, perPageNum);
    }

    /**
     * 保存对象，可实现对于已有的对象更新，新的对象增加
     *
     * @param o 对象类
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-4-14 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public void merge(Object o) {
        getHibernateTemplate().merge(o);
    }

    /**
     * @param entities 数据集
     * @author: liujr
     * @修改记录： ==============================================================<br>
     * 日期: 2007-4-11 liujr 创建方法，并实现其功能 从数据库删除指定数据集的记录
     * <p/>
     * ==============================================================<br>
     */
    public void deleteAll(Collection entities) {

        getHibernateTemplate().deleteAll(entities);

    }

    /**
     * 根据JDBCSQL语句查询数据
     *
     * @param sql 查询语句
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期: 2007-6-8 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public List findListByJDBCSQL(final String sql) {
        if (BaseDaoHibernate.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) getHibernateTemplate().execute(
                    new HibernateCallback() {
                        public Object doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            List list = new ArrayList();
                            Query query = session.createSQLQuery(sql);
                            list = query.list();
                            return list;
                        }
                    });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return findFirstPageInfoByJDBCSQLAndParams(sql, null, 1, BaseDaoHibernate.forcePageNum,
                    false).getDataList();
        }
    }

    /**
     * 获取单一值
     *
     * @param sql
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     * 日期:2008-6-12 zfz 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByJDBCSQL(final String sql) {
        String result = "";
        List list = findListByJDBCSQL(sql);
        if (list != null && list.size() > 0) {
            result = StrUtil.strnull(list.get(0));
        }
        return result;
    }

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
    public String getSingleValueByJDBCAndParamsSQL(final String sql, final List params) {
        String result = "";
        List list = findListByJDBCSQLAndParams(sql, params);
        if (list != null && list.size() > 0) {
            result = StrUtil.strnull(list.get(0));
        }
        return result;
    }

    /**
     * 根据hql查询唯一值
     *
     * @param hql
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     * 日期:Sep 4, 2008      zfz         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByHql(final String hql) {
        String result = "";
        List list = this.findByHql(hql);
        if (list != null && list.size() > 0) {
            result = StrUtil.strnull(list.get(0));
        }
        return result;
    }

    /**
     * 根据JDBC SQL语句分页查询
     *
     * @param sql         查询语句
     * @param params      参数
     * @param currentPage 单前页
     * @param perPageNum  每页记录数
     * @param isGroupby   是否分组查询
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-8 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;

                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }

                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");

                        String totalCountSql = "select count(*) "
                                + sqlStr.substring(sqlStr.indexOf("FROM "));
                        //        + sqlStr.substring(sqlStr.lastIndexOf("FROM "));


                        Query totalQuery = session
                                .createSQLQuery(totalCountSql);
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    totalQuery.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    totalQuery.setString(k, params.get(k)
                                            .toString());
                                }
                                if (param instanceof Long) {
                                    totalQuery.setLong(k,
                                            ((Long) params.get(k)).longValue());
                                }
                                if (param instanceof Date) {
                                    totalQuery.setDate(k,
                                            ((Date) params.get(k)));
                                }
                            }
                        }

                        List countList = totalQuery.list();

                        // 获取总记录数
                        int totalCounts = 0;

                        // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                        if (!isGroupby
                                || (sqlStr.indexOf("Group by") == -1
                                && sqlStr.indexOf("group by") == -1 && sqlStr
                                .indexOf("Group By") == -1)) {

                            // 防止countList.size=0时越界访问get(0)
                            if (countList.size() > 0) {
                                Long l = new Long(countList.get(0).toString());
                                totalCounts = l.intValue();
                            } else {
                                totalCounts = 0;
                            }
                        } else {
                            totalCounts = countList.size();
                        }

                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }
                        // 计算总页数
                        int totalPages = totalCounts / perPageSize;
                        totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                                : totalPages;

                        Query query = session.createSQLQuery(sqlStr);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

                        pageInfo.setTotalCount(totalCounts);
                        pageInfo.setTotalPageCount(totalPages);
                        pageInfo.setCurrentPage(currentPageNum);
                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * 根据JDBC SQL语句分页查询
     *
     * @param sql         查询语句
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @param isGroupby   是否分组
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-29 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQL(final String sql,
                                          final int currentPage, final int perPageNum, final boolean isGroupby) {
        return findPageInfoByJDBCSQLAndParams(sql, null, currentPage,
                perPageNum, isGroupby);
    }

    /**
     * 取SEQ
     *
     * @param seqName 序列名称
     * @return String
     * @author: panchh
     * @修改记录： ==============================================================<br>
     * 日期:2007-6-21 panchh 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public String getSeqNextval(String seqName) {
        String retStr = "0";
        String sql = "select " + seqName + ".nextval from dual";
        List list = findListByJDBCSQL(sql);
        if (list != null && list.size() > 0) {
            Object obj = (Object) list.get(0);
            retStr = obj.toString();
        }

        return retStr;
    }

    /**
     * @param sql    HQL语句
     * @param params 参数
     * @return List
     * @author: liujr
     * @修改记录： ==============================================================<br>
     * 日期:2007-10-29      liujr         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByHQLAndParams(final String sql, final List params) {
        if (BaseDaoHibernate.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) getHibernateTemplate().execute(
                    new HibernateCallback() {
                        public Object doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            List list = new ArrayList();

                            String sqlStr = sql;

                            sqlStr = sqlStr.replaceAll("from ", "FROM ");
                            sqlStr = sqlStr.replaceAll("From ", "FROM ");
                            Query query = session.createQuery(sqlStr);

                            // 判断是否有传入参数
                            if (params != null) {
                                for (int k = 0; k < params.size(); k++) {

                                    Object param = params.get(k);

                                    if (param instanceof Integer) {
                                        query.setInteger(k, ((Integer) params
                                                .get(k)).intValue());
                                    }
                                    if (param instanceof String) {
                                        query.setString(k, params.get(k)
                                                .toString());
                                    }
                                    if (param instanceof Long) {
                                        query.setLong(k, ((Long) params.get(k))
                                                .longValue());
                                    }
                                    if (param instanceof Date) {
                                        query
                                                .setDate(k, ((Date) params
                                                        .get(k)));
                                    }
                                }
                            }

                            list = query.list();

                            if (list == null) {
                                list = new ArrayList(0);
                            }

                            return list;
                        }
                    });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return this.findFirstPageInfoByHQLAndParams(sql, params, 1,
                    BaseDaoHibernate.forcePageNum).getDataList();
        }
    }

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
    public List findListByJDBCSQLAndParams(final String sql, final List params) {
        if (BaseDaoHibernate.forcePageNum < 1) {
            // 如果小于1，则不强制进行分页
            return (List) getHibernateTemplate().execute(
                    new HibernateCallback() {
                        public Object doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            List list = new ArrayList();

                            String sqlStr = sql;

                            sqlStr = sqlStr.replaceAll("from ", "FROM ");
                            sqlStr = sqlStr.replaceAll("From ", "FROM ");
                            Query query = session.createSQLQuery(sqlStr);

                            // 判断是否有传入参数
                            if (params != null) {
                                for (int k = 0; k < params.size(); k++) {

                                    Object param = params.get(k);

                                    if (param instanceof Integer) {
                                        query.setInteger(k, ((Integer) params
                                                .get(k)).intValue());
                                    }
                                    if (param instanceof String) {
                                        query.setString(k, params.get(k)
                                                .toString());
                                    }
                                    if (param instanceof Long) {
                                        query.setLong(k, ((Long) params.get(k))
                                                .longValue());
                                    }
                                    if (param instanceof Date) {
                                        query
                                                .setDate(k, ((Date) params
                                                        .get(k)));
                                    }
                                }
                            }

                            list = query.list();

                            if (list == null) {
                                list = new ArrayList(0);
                            }

                            return list;
                        }
                    });
        } else {
            // 如果大于等于1,则默认返回第一页的信息
            return findFirstPageInfoByJDBCSQLAndParams(sql, params, 1,
                    BaseDaoHibernate.forcePageNum, false).getDataList();
        }
    }

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
                                       String colName) throws ManagerException {
        String comment = "";
        String sql = "select comments from all_col_comments";
        if (StrUtil.strnull(owner).equals("")) {
            sql += " where owner = 'CRM' ";
        } else {
            sql += " where owner = '" + owner + "'";
        }
        sql += " and table_name = '" + tableName + "'";
        sql += " and column_name ='" + colName + "'";
        List list = findListByJDBCSQL(sql);
        for (int i = 0; list != null && i < list.size(); i++) {
            Object obj = list.get(i);
            comment = StrUtil.strnull(obj);
        }
        return comment;
    }

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
    public String getSingleValueByHqlAndParams(String hql, List params) {
        String result = "";
        List list = this.findListByHQLAndParams(hql, params);
        if (list != null && list.size() > 0) {
            result = StrUtil.strnull(list.get(0));
        }
        return result;
    }

    /**
     * 获取单一值，绑定变量
     *
     * @param sql
     * @param params
     * @return
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2008-12-31 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getSingleValueByJDBCSQLAndParams(String sql, List params) {
        String result = "";
        List list = findListByJDBCSQLAndParams(sql, params);
        if (list != null && list.size() > 0) {
            result = StrUtil.strnull(list.get(0));
        }
        return result;
    }

    /**
     * 根据hibernate的Hql语句和参数查询出分页信息
     *
     * @param sql         查询语句
     * @param params      查询参数
     * @param currentPage 当前页
     * @param perPageNum  每页记录数
     * @return PageInfo
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-3-29 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public PageInfo findFirstPageInfoByHQLAndParams(final String sql,
                                                    final List params, final int currentPage, final int perPageNum) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;

                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }


                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }


                        Query query = session.createQuery(sqlStr);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

//                        pageInfo.setTotalCount(totalCounts);
//                        pageInfo.setTotalPageCount(totalPages);
//                        pageInfo.setCurrentPage(currentPageNum);
//                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @return
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:Mar 20, 2009      wuq         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findFirstPageInfoByJDBCSQLAndParams(final String sql,
                                                        final List params, final int currentPage, final int perPageNum,
                                                        final boolean isGroupby) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;

                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }

                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }


                        Query query = session.createSQLQuery(sqlStr);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

//                        pageInfo.setTotalCount(totalCounts);
//                        pageInfo.setTotalPageCount(totalPages);
//                        pageInfo.setCurrentPage(currentPageNum);
//                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * @param sql
     * @param zz
     * @return
     * @author: nip
     * @修改记录： ==============================================================<br>
     * 日期:May 22, 2009      nip         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByJDBCSQL(final String sql, final Class zz) {

        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                List list = new ArrayList();
                Query query = session.createSQLQuery(sql).addEntity(zz);
                list = query.list();
                return list;
            }
        });
    }

    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @param zz
     * @return
     * @author: nip
     * @修改记录： ==============================================================<br>
     * 日期:May 22, 2009      nip         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby, final Class zz) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;

                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }

                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");

                        String totalCountSql = "select count(*) "
                                + sqlStr.substring(sqlStr.indexOf("FROM "));
                        // + sqlStr.substring(sqlStr.lastIndexOf("FROM "));

                        Query totalQuery = session
                                .createSQLQuery(totalCountSql);
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    totalQuery.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    totalQuery.setString(k, params.get(k)
                                            .toString());
                                }
                                if (param instanceof Long) {
                                    totalQuery.setLong(k,
                                            ((Long) params.get(k)).longValue());
                                }
                                if (param instanceof Date) {
                                    totalQuery.setDate(k,
                                            ((Date) params.get(k)));
                                }
                            }
                        }

                        List countList = totalQuery.list();

                        // 获取总记录数
                        int totalCounts = 0;

                        // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                        if (!isGroupby
                                || (sqlStr.indexOf("Group by") == -1
                                && sqlStr.indexOf("group by") == -1 && sqlStr
                                .indexOf("Group By") == -1)) {

                            // 防止countList.size=0时越界访问get(0)
                            if (countList.size() > 0) {
                                Long l = new Long(countList.get(0).toString());
                                totalCounts = l.intValue();
                            } else {
                                totalCounts = 0;
                            }
                        } else {
                            totalCounts = countList.size();
                        }

                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }
                        // 计算总页数
                        int totalPages = totalCounts / perPageSize;
                        totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                                : totalPages;

                        Query query = session.createSQLQuery(sqlStr).addEntity(
                                zz);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

                        pageInfo.setTotalCount(totalCounts);
                        pageInfo.setTotalPageCount(totalPages);
                        pageInfo.setCurrentPage(currentPageNum);
                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * @param sql
     * @param totalCountSql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @param isGroupby
     * @return
     * @author: newlock
     * @修改记录： ==============================================================<br>
     * 日期:2010-6-23      liuxl         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql, final String countSql,
                                                   final List params, final int currentPage, final int perPageNum,
                                                   final boolean isGroupby) {

        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();

                        String sqlStr = sql;
                        String countSqlStr = countSql;
                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }

                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }

                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");

                        String totalCountSql = "";
                        if (!"".equals(countSqlStr)) {
                            totalCountSql = countSqlStr;
                        } else {
                            totalCountSql = "select count(*) "
                                    + sqlStr.substring(sqlStr.indexOf("FROM "));
                        }


                        Query totalQuery = session
                                .createSQLQuery(totalCountSql);
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {
                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    totalQuery.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    totalQuery.setString(k, params.get(k)
                                            .toString());
                                }
                                if (param instanceof Long) {
                                    totalQuery.setLong(k,
                                            ((Long) params.get(k)).longValue());
                                }
                                if (param instanceof Date) {
                                    totalQuery.setDate(k,
                                            ((Date) params.get(k)));
                                }
                            }
                        }

                        List countList = totalQuery.list();

                        // 获取总记录数
                        int totalCounts = 0;

                        // 以下是处理sql语句中有"group by"的时候取总记录数就是取list的个数
                        if (!isGroupby
                                || (sqlStr.indexOf("Group by") == -1
                                && sqlStr.indexOf("group by") == -1 && sqlStr
                                .indexOf("Group By") == -1)) {

                            // 防止countList.size=0时越界访问get(0)
                            if (countList.size() > 0) {
                                Long l = new Long(countList.get(0).toString());
                                totalCounts = l.intValue();
                            } else {
                                totalCounts = 0;
                            }
                        } else {
                            totalCounts = countList.size();
                        }

                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }
                        // 计算总页数
                        int totalPages = totalCounts / perPageSize;
                        totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                                : totalPages;

                        Query query = session.createSQLQuery(sqlStr);

                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);

                        // sql=hsql+ " limit " + (pageNo-1)*page_size + ","
                        // +page_size;

                        list = query.list();

                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();

                        pageInfo.setTotalCount(totalCounts);
                        pageInfo.setTotalPageCount(totalPages);
                        pageInfo.setCurrentPage(currentPageNum);
                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);

                        return pageInfo;
                    }
                });
    }

    /**
     * @param sql
     * @param params
     * @param currentPage
     * @param perPageNum
     * @return
     * @author: liuxl
     * @修改记录： 分页优化，使用over()函数实现一条语句来统计分页
     * 使用时需把over()函数放在开头 (SELECT count(1) over()  cn,* from 表名
     * ==============================================================<br>
     * 日期:2011-3-14      liuxl         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public PageInfo findPageInfoByJDBCSQLAndParams(final String sql,
                                                   final List params, final int currentPage, final int perPageNum) {
        return (PageInfo) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        PageInfo pageInfo = new PageInfo();
                        List list = new ArrayList();
                        String sqlStr = sql;
                        int currentPageNum = currentPage;
                        int perPageSize = perPageNum;

                        if (BaseDaoHibernate.forceMaxPageNum >= 1) {
                            // 如果有强制控制返回的最大分页记录数
                            if (perPageSize > BaseDaoHibernate.forceMaxPageNum) {
                                perPageSize = BaseDaoHibernate.forceMaxPageNum;
                            }
                        }
                        if (currentPageNum == 0) {
                            currentPageNum = 1;
                        }
                        sqlStr = sqlStr.replaceAll("from ", "FROM ");
                        sqlStr = sqlStr.replaceAll("From ", "FROM ");
                        if (perPageSize == 0) {
                            perPageSize = 10;
                        }
                        Query query = session.createSQLQuery(sqlStr);
                        // 判断是否有传入参数
                        if (params != null) {
                            for (int k = 0; k < params.size(); k++) {

                                Object param = params.get(k);

                                if (param instanceof Integer) {
                                    query.setInteger(k, ((Integer) params
                                            .get(k)).intValue());
                                }
                                if (param instanceof String) {
                                    query
                                            .setString(k, params.get(k)
                                                    .toString());
                                }
                                if (param instanceof Long) {
                                    query.setLong(k, ((Long) params.get(k))
                                            .longValue());
                                }
                                if (param instanceof Date) {
                                    query.setDate(k, ((Date) params.get(k)));
                                }
                            }
                        }

                        // 获取开始记录
                        int firstNum = currentPageNum * perPageSize
                                - perPageSize;

                        query.setFirstResult(firstNum);
                        query.setMaxResults(perPageSize);
                        list = query.list();

                        //start 计算分页
                        // 获取总记录数
                        int totalCounts = 0;

                        // 防止countList.size=0时越界访问get(0)
                        if (list != null && list.size() > 0) {
                            Object[] objs = (Object[]) list.get(0);
                            Long l = new Long(objs[0].toString());
                            totalCounts = l.intValue();
                        } else {
                            totalCounts = 0;
                        }
                        // 计算总页数
                        int totalPages = totalCounts / perPageSize;
                        totalPages = (totalCounts % perPageSize) > 0 ? (totalPages + 1)
                                : totalPages;
                        //end
                        if (list == null) {
                            list = new ArrayList(0);
                        }
                        // 当取当前页的条数
                        // perPageSize=list.size();
                        pageInfo.setTotalCount(totalCounts);
                        pageInfo.setTotalPageCount(totalPages);
                        pageInfo.setCurrentPage(currentPageNum);
                        pageInfo.setPerPageCount(perPageSize);
                        pageInfo.setDataList(list);
                        return pageInfo;
                    }
                });
    }

    /**
     * sql查询最大数量
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
                                           final int maxCount) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                List list = new ArrayList();

                String sqlStr = sql;

                sqlStr = sqlStr.replaceAll("from ", "FROM ");
                sqlStr = sqlStr.replaceAll("From ", "FROM ");
                Query query = session.createSQLQuery(sqlStr);

                // 判断是否有传入参数
                if (params != null) {
                    for (int k = 0; k < params.size(); k++) {

                        Object param = params.get(k);

                        if (param instanceof Integer) {
                            query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                        }
                        if (param instanceof String) {
                            query.setString(k, params.get(k).toString());
                        }
                        if (param instanceof Long) {
                            query
                                    .setLong(k, ((Long) params.get(k))
                                            .longValue());
                        }
                        if (param instanceof Date) {
                            query.setDate(k, ((Date) params.get(k)));
                        }
                    }
                }
                query.setMaxResults(maxCount);
                list = query.list();

                if (list == null) {
                    list = new ArrayList(0);
                }

                return list;
            }
        });
    }

    /**
     * HQL查询最大数量
     *
     * @param sql
     * @param params
     * @param maxCount
     * @return
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:Mar 27, 2012      tanyw         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List findListByHQLAndParams(final String sql, final List params,
                                       final int maxCount) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                List list = new ArrayList();

                String sqlStr = sql;

                sqlStr = sqlStr.replaceAll("from ", "FROM ");
                sqlStr = sqlStr.replaceAll("From ", "FROM ");
                Query query = session.createQuery(sqlStr);

                // 判断是否有传入参数
                if (params != null) {
                    for (int k = 0; k < params.size(); k++) {

                        Object param = params.get(k);

                        if (param instanceof Integer) {
                            query.setInteger(k, ((Integer) params.get(k))
                                    .intValue());
                        }
                        if (param instanceof String) {
                            query.setString(k, params.get(k).toString());
                        }
                        if (param instanceof Long) {
                            query
                                    .setLong(k, ((Long) params.get(k))
                                            .longValue());
                        }
                        if (param instanceof Date) {
                            query.setDate(k, ((Date) params.get(k)));
                        }
                    }
                }

                query.setMaxResults(maxCount);
                list = query.list();

                if (list == null) {
                    list = new ArrayList(0);
                }

                return list;
            }
        });
    }

    public Object qryZbInfo(final String sql, final List params, final Class cl) {
        Object obj = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session s)
                    throws HibernateException, SQLException {
                Query query = s.createSQLQuery(sql);
                for (int i = 0; i < params.size(); i++) {
                    query.setString(i, params.get(i).toString());
                }
                List result = query.list();
                return null;
            }

        });
        return null;
    }

    public void updateBySql(String seq, List params) {
        if (params == null || params.size() < 1)
            getHibernateTemplate().bulkUpdate(seq);
        else
            getHibernateTemplate().bulkUpdate(seq, params);
    }

    public void executeSql(final String sql, final List params) {
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session s)
                    throws HibernateException, SQLException {
                Query query = s.createSQLQuery(sql);
                for (int k = 0; k < params.size(); k++) {
                    Object param = params.get(k);
                    if (param instanceof Integer) {
                        query.setInteger(k, ((Integer) params.get(k))
                                .intValue());
                    }
                    if (param instanceof String) {
                        query.setString(k, params.get(k).toString());
                    }
                    if (param instanceof Long) {
                        query
                                .setLong(k, ((Long) params.get(k))
                                        .longValue());
                    }
                    if (param instanceof Date) {
                        query.setDate(k, ((Date) params.get(k)));
                    }
                }
                return query.executeUpdate() + "";
            }
        });
    }

    //执行sql语句进行发票数据更新
    public void updateBySqlForInvoice(final String sql, final List params) {
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                for (int i = 0; i < params.size(); i++) {
                    if (params.get(i) instanceof Integer) {
                        query.setInteger(i, ((Integer) params.get(i)).intValue());
                    } else if (params.get(i) instanceof Long) {
                        query.setLong(i, ((Long) params.get(i)).longValue());
                    } else if (params.get(i) instanceof String) {
                        query.setString(i, (String) params.get(i));
                    } else if (params.get(i) instanceof Date) {
                        query.setDate(i, ((Date) params.get(i)));
                    }
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see com.wuye2.base.repositories.CrmRepository#jdbcFindList(java.lang.String,
     * java.lang.Class, java.util.List)
     */
    public <E> List<E> jdbcFindList(final String sql, final Class<E> elementType,
                                    final List<Object> params) {
        Object[] array = null;
        if (params != null) {
            array = new Object[params.size()];
            params.toArray(array);
        }
        return this.executeJdbc(sql, elementType, array);
    }

    /**
     * 执行JDBC查询语句，并获得数据列表。
     *
     * @param <E>         实体类型
     * @param sql         SQL语句
     * @param elementType 实体Class
     * @param params      参数列表
     * @return 数据列表
     */
    @SuppressWarnings("unchecked")
    private <E> List<E> executeJdbc(final String sql, final Class<E> elementType,
                                    final Object[] params) {
        final List<E> data = new ArrayList<E>();
        final RowMapper<E> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(elementType);
        this.getJdbcTemplate().query(sql, params, new ResultSetExtractor() {
            public Object extractData(final ResultSet rs) throws SQLException {
                final List pageItems = data;
                int currentRow = 0;
                final int startRow = BaseDaoHibernate.this.getFirst();
                final int lastRow = startRow + BaseDaoHibernate.this.getLast();
                while (rs.next() && (lastRow == 0 || currentRow < lastRow)) {
                    if (currentRow >= startRow) {
                        pageItems.add(rowMapper.mapRow(rs, currentRow));
                    }
                    currentRow++;
                }
                return data;
            }
        });
        return data;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.ffcs.crm2.base.repositories.CrmRepository#jdbcFindPageInfo(java.lang.String,
     * java.lang.Class, java.util.List, int, int)
     */
    public <E> PageInfo jdbcFindPageInfo(final String sql, final Class<E> elementType,
                                         final List<Object> params, final int currentPage, final int perPageNum) {
        int pageSize = perPageNum;
        if (pageSize == 0) {
            pageSize = BaseConstants.DEFAULT_PAGE_PER_COUNT;
        }
        this.setFirst((currentPage - 1) * pageSize);
        // this.setLast(currentPage * perPageNum);
        this.setLast(pageSize);

        Object[] array = null;
        if (params != null) {
            array = new Object[params.size()];
            params.toArray(array);
        }
        final List<E> list = this.executeJdbc(sql, elementType, array);
        final int totalCounts = this.jdbcGetSize(sql, params);
        int totalPages = totalCounts / pageSize;
        if ((totalCounts % pageSize) > 0) {
            totalPages += 1;
        }

        final PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(totalCounts);
        pageInfo.setTotalPageCount(totalPages);
        pageInfo.setCurrentPage(currentPage);
        pageInfo.setPerPageCount(pageSize);
        pageInfo.setDataList(list);

        return pageInfo;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.ffcs.crm2.base.repositories.CrmRepository#jdbcGetSize(java.lang.String,
     * java.util.List)
     */
    public int jdbcGetSize(final String sql, final List<Object> params) {
        final String totalCountSql = getTotalCountSql(sql);

        Object[] array = null;
        if (params != null) {
            array = new Object[params.size()];
            params.toArray(array);
        }
        return this.jdbcQueryForInt(totalCountSql, array);
    }

    private String getTotalCountSql(final String sql) {
        final StringBuffer buffer = new StringBuffer();
        final String sqlString = sql.trim();
        final int iIndex = (" " + sqlString.toLowerCase()).indexOf(" from ");
        String sqlHeaderString = sqlString.substring(0, iIndex);
        // if (sqlHeaderString.toLowerCase().indexOf("select") != -1) {
        // sqlHeaderString = sqlString.substring(6, iIndex);
        // }
        if (sqlHeaderString.toLowerCase().indexOf("distinct") == -1) {
            buffer.append("select count(*) ").append(sqlString.substring(iIndex));
        } else {
            buffer.append("select count(*) from (").append(sqlString).append(") ");
        }

        //增加处理，将count语句的order by 去除
        String countSql = buffer.toString();

        return countSql;
    }

    public int jdbcQueryForInt(final String sql, final Object... args) {
        return this.getJdbcTemplate().queryForInt(sql, args);
    }

    /**
     * 通过class_id和对象id查询相应对象
     *
     * @param classId
     * @param objId
     * @return
     */
    public BaseEntity getObj(String classId, Integer objId) {
        if (BaseConstants.CLASS_USER.equals(classId)) {
            return (User) this.getObject(User.class, objId);
        } else if (BaseConstants.CLASS_COMPANY.equals(classId)) {
            return (PropertyCompany) this.getObject(PropertyCompany.class, objId);
        } else if (BaseConstants.CLASS_COMMUNITY.equals(classId)) {
            return (Community) this.getObject(Community.class, objId);
        } else if (BaseConstants.CLASS_ORG.equals(classId)) {
            return (Organization) this.getObject(Organization.class, objId);
        } else if (BaseConstants.CLASS_BUILDING.equals(classId)) {
            return (Building) this.getObject(Building.class, objId);
        } else if (BaseConstants.CLASS_ROOM.equals(classId)) {
            return (Room) this.getObject(Room.class, objId);
        }
        return null;
    }
}