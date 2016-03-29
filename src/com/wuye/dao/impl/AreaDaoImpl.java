package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.dao.AreaDao;
import com.wuye.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoHibernate implements AreaDao {

    public List<Area> getAreas(int upAreaId) {
        String hql = "select a.* from area a where a.up_area_id = ?";
        List params = new ArrayList();
        params.add(upAreaId);
        return this.jdbcFindList(hql, Area.class, params);
    }

}
