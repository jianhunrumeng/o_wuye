package com.wuye.common.services.impl;

import com.wuye.common.dao.Dao;
import com.wuye.common.services.BaseManager;
import com.wuye.entity.BaseEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.service.impl.BaseManager.java
 * @所含类：BaseManager
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
@Service("manager")
public class BaseManagerImpl implements BaseManager {
    /**
     * log Log
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * dao Dao
     */
    @Autowired
    protected Dao dao = null;

    /**
     * 保存实体
     *
     * @param baseEntity
     */
    public void save(BaseEntity baseEntity) {
        baseEntity.save();
    }

    /**
     * @param dao dao
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    /**
     * @param vdao Dao
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void personDao(Dao vdao) {
        this.dao = vdao;
    }

    /**
     * @param clazz Class
     * @param id    Serializable
     * @return Object
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Object getObject(Class clazz, Serializable id) {
        return dao.getObject(clazz, id);
    }

    /**
     * @param clazz Class
     * @return List
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public List getObjects(Class clazz) {
        return dao.getObjects(clazz, null);
    }

    /**
     * @param clazz Class
     * @param id    Serializable
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void removeObject(Class clazz, Serializable id) {
        dao.removeObject(clazz, id);
    }

    /**
     * @param o Object
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void saveObject(Object o) {
        dao.saveObject(o);
    }

    /**
     * add by tan
     * 20151115
     * 实体失效1100
     */
    public void updateObjectInvalid(Object o) {
        try {
            BeanUtils.copyProperty(o, "statusCd", "1100");
        } catch (Exception e) {

        }
        dao.saveObject(o);
    }
}
