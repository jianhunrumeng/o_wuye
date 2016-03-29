package com.wuye.entity;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.date.DateUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

public class BaseEntity implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Date createDate;
    protected Date updateDate;
    protected String statusCd;
    protected String statusName;
    protected String objClassId;
    protected Date statusDate;
    private Integer id;

    public Set<String> setPropertyLoaded = new HashSet<String>();

    private boolean isNewEntity = false;
    private boolean isSaved = false;

    public BaseEntity() {
        super();
    }

    public void save() {
        if (StrUtil.isNullOrEmpty(this.getStatusCd())) {
            this.setStatusCd(BaseConstants.STATUS_VALID);
        }
        if (StrUtil.isNullOrEmpty(this.getStatusDate())) {
            this.setStatusDate(DateUtil.getNewDate());
        }
        if (StrUtil.isNullOrEmpty(this.getCreateDate())) {
            this.setCreateDate(DateUtil.getNewDate());
        }
        this.setUpdateDate(DateUtil.getNewDate());
        BaseEntity.getDefaultDao().saveObject(this);
        if (this.isNewEntity()) {
            this.isSaved = true;
        }
    }

    public void remove() {
        BaseEntity.getDefaultDao().getHibernateTemplate().delete(this);
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatusCd() {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public Date getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public static BaseDaoHibernate getDefaultDao() {
        BaseDaoHibernate baseDaoHibernate = (BaseDaoHibernate) SpringUtil
                .getBean("baseDaoHibernate");
        return baseDaoHibernate;
    }

    public void Loaded(String propertyName) {
        setPropertyLoaded.add(propertyName);
    }

    /**
     * 如果是lazy 属性 session 被关闭，重新关联一下 session
     *
     * @param propertyName
     * @param property
     * @return
     * @author wangzy 2011-3-30 wangzy
     */
    public synchronized boolean isLoaded(String propertyName, Object property) {
        if (isLoaded(propertyName)) {
            return true;
        }

        if (property instanceof Collection || property instanceof Map)
            return false;

        if (!Hibernate.isInitialized(property)
                && property instanceof BaseEntity) {
            HibernateProxy proxy = (HibernateProxy) property;

            boolean isLock = false;
            if (proxy.getHibernateLazyInitializer().getSession() == null) {
                this.getDefaultDao().getHibernateTemplate()
                        .lock(property, LockMode.NONE);
                isLock = true;
            }

            Hibernate.initialize(property);

            this.Loaded(propertyName);

            if (isLock) {
                this.getDefaultDao().getHibernateTemplate().evict(this);
            }
            return true;
        }

        if (property != null)
            return true;

        return false;
    }

    private boolean isLoaded(String propertyName) {
        if (isNewEntity())
            return true;
        if (isSaved == true)
            return true;

        return getSetPropertyLoaded().contains(propertyName);
    }

    public boolean isNewEntity() {
        return (getId() == null || isNewEntity);
    }

    public String getStatusName() {
        if ("1000".equals(this.getStatusCd())) {
            this.statusName = "有效";
        } else if ("1100".equals(this.getStatusCd())) {
            this.statusName = "失效";
        } else {
            this.statusName = "未知状态";
        }

        return this.statusName;
    }

    public String getObjClassId() {
        if (this.isLoaded("classId")) {
            return this.objClassId;
        }
        if (this instanceof User) {
            this.objClassId = BaseConstants.CLASS_USER;
        } else if (this instanceof PropertyCompany) {
            this.objClassId = BaseConstants.CLASS_COMPANY;
        } else if (this instanceof Community) {
            this.objClassId = BaseConstants.CLASS_COMMUNITY;
        } else if (this instanceof Organization) {
            this.objClassId = BaseConstants.CLASS_ORG;
        } else if (this instanceof Building) {
            this.objClassId = BaseConstants.CLASS_BUILDING;
        } else if (this instanceof Room) {
            this.objClassId = BaseConstants.CLASS_ROOM;
        } else {
            this.objClassId = "";
        }
        this.Loaded("classId");
        return this.objClassId;

    }

    public Integer getId() {
        return this.id;
    }

    public Set<String> getSetPropertyLoaded() {
        return this.setPropertyLoaded;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSetPropertyLoaded(Set<String> setPropertyLoaded) {
        this.setPropertyLoaded = setPropertyLoaded;
    }

    public void setNewEntity(boolean isNewEntity) {
        this.isNewEntity = isNewEntity;
    }

    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
}