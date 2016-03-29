package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.AttrSpec;

public interface CommonServiceManager extends BaseManager {
    /**
     * 独立事物
     *
     * @param attr
     */
    public void saveAttrSpec(AttrSpec attr);
}
