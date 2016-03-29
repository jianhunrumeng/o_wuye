package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.entity.BaseEntity;
import com.wuye.entity.PartyInfo;

public interface PartyInfoDao extends Dao {
    public PartyInfo getPartyInfo(String classId, Integer objId);

    public BaseEntity getObj(String classId, Integer objId);
}
