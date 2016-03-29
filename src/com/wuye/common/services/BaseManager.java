package com.wuye.common.services;

import com.wuye.common.dao.Dao;
import com.wuye.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.service.Manager.java
 * @所含类：Manager
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 wuq 创建
 * <p/>
 * =============================================================<br>
 */
public interface BaseManager {

    public void save(BaseEntity baseEntity);

    /**
     * Expose the setDao method for testing purposes
     *
     * @param dao the type of Dao
     */
    public void setDao(Dao dao);

    /**
     * Generic method used to get a all objects of a particular type.
     *
     * @param clazz the type of objects
     * @return List of populated objects
     */
    public List getObjects(Class clazz);

    /**
     * Generic method to get an object based on class and identifier.
     *
     * @param clazz model class to lookup
     * @param id    the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    public Object getObject(Class clazz, Serializable id);

    /**
     * Generic method to save an object.
     *
     * @param o the object to save
     */
    public void saveObject(Object o);

    /**
     * Generic method to delete an object based on class and id
     *
     * @param clazz model class to lookup
     * @param id    the identifier of the class
     */
    public void removeObject(Class clazz, Serializable id);

    /**
     * Generic method to delete an object based on class and id
     * 实体设置为失效状态
     *
     * @param clazz model class to lookup
     * @param id    the identifier of the class
     */
    public void updateObjectInvalid(Object o);
}
