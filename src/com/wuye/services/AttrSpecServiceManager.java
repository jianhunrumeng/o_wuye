package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.RetVO;

import java.util.Map;

public interface AttrSpecServiceManager extends BaseManager {
    public RetVO getAttrSpec(String attrCd);

    public RetVO getAttrSpec(Map<String, Object> map);

    public RetVO getAttrValue(Map<String, Object> map);

    public RetVO save(Map<String, Object> map);
}
