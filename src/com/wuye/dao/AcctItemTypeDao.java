package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;

import java.util.Map;

public interface AcctItemTypeDao extends Dao {
    public PageInfo getAcctItemType(Map<String, Object> map);
}
