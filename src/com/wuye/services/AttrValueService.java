package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.AttrValue;

import java.util.List;

public interface AttrValueService extends BaseManager {
    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  String attrValue);

    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  Integer communityId, String attrValue);

    /**
     * 获取小区的属性值,如果containSysValue=true，就把系统的属性值也一并返回 .
     *
     * @param classId
     * @param attrCd
     * @param communityId
     * @param containSysValue
     * @return
     */
    public List<AttrValue> getAttrValue(Integer classId, String attrCd,
                                        Integer communityId, boolean containSysValue);

    /**
     * 获取属性值
     * .
     *
     * @param classCode
     * @param attrCd
     * @return
     * @author FFCS-ZHENGGW
     * 2016-1-2 FFCS-ZHENGGW
     */
    public List<AttrValue> getAttrValueList(String classCode, String attrCd);

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
