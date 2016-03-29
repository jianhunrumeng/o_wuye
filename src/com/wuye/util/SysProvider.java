package com.wuye.util;

import com.wuye.constants.BaseConstants;
import com.wuye.entity.AttrSpec;
import com.wuye.entity.BaseEntity;
import com.wuye.entity.SysClass;

import java.util.ArrayList;
import java.util.List;

public class SysProvider {
    public static AttrSpec getAttrSpec(String className, String propertyName) {
        SysClass entityModel = getEntityByName(className);
        if (entityModel == null)
            return null;

        return entityModel.getAttrSpecByCode(propertyName);
    }

    private static SysClass getEntityByName(String className) {
        String hql = "from SysClass a where a.javaCode=? and a.statusCd = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(className);
        params.add(BaseConstants.STATUS_VALID);

        List<SysClass> sys = BaseEntity.getDefaultDao().findListByHQLAndParams(hql, params);
        if (sys != null && sys.size() > 0) {
            return sys.get(0);
        } else {
            return null;
        }
    }
}
