package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AttrValueDao;
import com.wuye.entity.AttrValue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "attrValueDao")
public class AttrValueDaoImpl extends BaseDaoHibernate implements AttrValueDao {
    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  Integer communityId, String attrValue, boolean containSysValue) {
        AttrValue value = null;
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        if (containSysValue) {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and (b.community = ? or b.community is null) ")
                    .append(" and b.statusCd = ? ")
                    .append(" and b.attrValue = ？ ");

        } else {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and b.community = ? ")
                    .append(" and b.statusCd = ? ")
                    .append(" and b.attrValue = ？ ");
        }

        params.add(classId);
        params.add(communityId);
        params.add(BaseConstants.STATUS_VALID);
        params.add(attrValue);

        List<AttrValue> attrVales = this.findListByHQLAndParams(
                hql.toString(), params);
        if (attrVales != null && attrVales.size() > 0) {
            value = attrVales.get(0);
        }
        return value;
    }

}
