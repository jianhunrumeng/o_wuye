package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.entity.AttrValue;

public interface AttrValueDao extends Dao {
    /**
     * 获取特定的属性
     * tanyw 20160110
     *
     * @param classId
     * @param attrCd
     * @param communityId
     * @param attrValue
     * @param containSysValue
     * @return
     */
    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  Integer communityId, String attrValue, boolean containSysValue);
}
