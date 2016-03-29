package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.entity.Area;

import java.util.List;

public interface AreaDao extends Dao {
    /**
     * //根据上级ID获取Area信息
     *
     * @param up_area_id
     * @return
     */
    List<Area> getAreas(int upAreaId);
}
