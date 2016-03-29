package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.RetVO;

import java.util.Map;

public interface AcctItemTypeServiceManager extends BaseManager {

    RetVO getAcctItemType(Map<String, Object> map);
}
