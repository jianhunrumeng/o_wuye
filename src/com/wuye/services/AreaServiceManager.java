package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.Area;

import java.util.List;

public interface AreaServiceManager extends BaseManager {
    /**
     * 根据上级ID获取Area信息
     *
     * @param upAreaId
     * @return
     */
    List<Area> getAreas(int upAreaId);
}
