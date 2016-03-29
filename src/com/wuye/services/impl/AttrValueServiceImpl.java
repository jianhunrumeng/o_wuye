package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AttrValueDao;
import com.wuye.entity.AttrValue;
import com.wuye.services.AttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("attrValueService")
public class AttrValueServiceImpl extends BaseManagerImpl implements
        AttrValueService {
    @Autowired
    AttrValueDao attrValueDao;

    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  String attrValue) {
        StringBuffer hql = new StringBuffer();
        hql.append("select b from AttrSpec a, AttrValue b ")
                .append(" where 1=1 ").append(" and a.classId = ? ")
                .append(" and a.attrId = b.attrSpec.attrId ")
                .append(" and b.statusCd = ? ").append(" and b.attrValue = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(classId);
        params.add(BaseConstants.STATUS_VALID);
        params.add(attrValue);
        List<AttrValue> attrVales = attrValueDao.findListByHQLAndParams(
                hql.toString(), params);
        if (attrVales != null && attrVales.size() > 0) {
            return attrVales.get(0);
        }
        return null;
    }

    public List<AttrValue> getAttrValueList(String classCode, String attrCd) {
        StringBuffer hql = new StringBuffer();
        hql.append("select b from AttrSpec a, AttrValue b, SysClass c ")
                .append(" where 1=1 ").append(" and a.classId = c.classId ")
                .append(" and a.attrId = b.attrSpec.attrId ")
                .append(" and b.statusCd = ? ").append(" and a.attrCode = ? ").append(" and c.javaCode = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(BaseConstants.STATUS_VALID);
        params.add(attrCd);
        params.add(classCode);
        List<AttrValue> attrVales = attrValueDao.findListByHQLAndParams(
                hql.toString(), params);
        return attrVales;
    }

    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  Integer communityId, String attrValue) {
        StringBuffer hql = new StringBuffer();
        hql.append("select b from AttrSpec a, AttrValue b ")
                .append(" where 1=1 ").append(" and a.classId = ? ")
                .append(" and a.attrId = b.attrSpec.attrId ")
                .append(" and b.community = ? ").append(" and b.statusCd = ? ")
                .append(" and b.attrValue = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(classId);
        params.add(communityId);
        params.add(BaseConstants.STATUS_VALID);
        params.add(attrValue);
        List<AttrValue> attrVales = attrValueDao.findListByHQLAndParams(
                hql.toString(), params);
        if (attrVales != null && attrVales.size() > 0) {
            return attrVales.get(0);
        }
        return null;
    }

    public List<AttrValue> getAttrValue(Integer classId, String attrCd,
                                        Integer communityId, boolean containSysValue) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        if (containSysValue) {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and (b.community = ? or b.community is null) ")
                    .append(" and b.statusCd = ? ");

        } else {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and b.community = ? ")
                    .append(" and b.statusCd = ? ");
        }

        params.add(classId);
        params.add(communityId);
        params.add(BaseConstants.STATUS_VALID);

        List<AttrValue> attrVales = attrValueDao.findListByHQLAndParams(
                hql.toString(), params);
        return attrVales;
    }

    public AttrValue getAttrValue(Integer classId, String attrCd,
                                  Integer communityId, String attrValue, boolean containSysValue) {
        AttrValue value = null;
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        if (containSysValue) {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrCode= ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and (b.communityId = ? or b.communityId is null) ")
                    .append(" and b.statusCd = ? ")
                    .append(" and b.attrValue = ? ");

        } else {
            hql.append("select b from AttrSpec a, AttrValue b ")
                    .append(" where 1=1 ").append(" and a.classId = ? ")
                    .append(" and a.attrCode= ? ")
                    .append(" and a.attrId = b.attrSpec.attrId ")
                    .append(" and b.communityId = ? ")
                    .append(" and b.statusCd = ? ")
                    .append(" and b.attrValue = ? ");
        }

        params.add(classId);
        params.add(attrCd);
        params.add(communityId + "");
        params.add(BaseConstants.STATUS_VALID);
        params.add(attrValue);

        List<AttrValue> attrVales = attrValueDao.findListByHQLAndParams(
                hql.toString(), params);
        if (attrVales != null && attrVales.size() > 0) {
            value = attrVales.get(0);
        }
        return value;
    }
}
