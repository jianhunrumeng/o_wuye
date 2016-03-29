package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AttrSpecDao;
import com.wuye.entity.AttrSpec;
import com.wuye.entity.AttrValue;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "attrSpecDao")
public class AttrSpecDaoImpl extends BaseDaoHibernate implements AttrSpecDao {

    public List<AttrSpec> getAttr(String attrCd) {

        String hql = "from AttrSpec a where a.attrCode = ? and a.statusCd = ? ";
        List<String> params = new ArrayList<String>();
        params.add(attrCd);
        params.add(BaseConstants.STATUS_VALID);
        List<AttrSpec> ret = super.findListByHQLAndParams(hql, params, BaseConstants.QUERY_ROW_MAX);
        return ret;
    }

    public List<AttrSpec> getAttr(Map<String, Object> map) {
        List<AttrSpec> ret = new ArrayList<AttrSpec>();
        JSONArray attrCds = (JSONArray) map.get("attrCd");

        if (attrCds != null) {
            for (Object attrCd : attrCds) {
                List<AttrSpec> tmpRet = this.getAttr(StrUtil
                        .strnull(attrCd));
                if (tmpRet != null && tmpRet.size() > 0) {
                    ret.addAll(tmpRet);
                }
            }
        }
        return ret;
    }

    /**
     * 查询系统默认的属性值
     *
     * @param map
     * @return
     */
    public List<AttrValue> getSysAttrValue(Map<String, Object> map) {
        Integer attrId = NumericUtil.nullToIntegerZero(map.get("attrId"));

        List<AttrValue> ret = null;
        if (attrId != null) {
            String hql = "from AttrValue a where a.attrId = ? and a.statusCd = ? and a.communityId is null ";
            List<Object> params = new ArrayList<Object>();
            params.add(attrId);
            params.add(BaseConstants.STATUS_VALID);
            ret = super.findListByHQLAndParams(hql, params, BaseConstants.QUERY_ROW_MAX);

        }
        return ret;
    }

    /**
     * 查询小区所属的属性值和系统默认的属性值
     *
     * @param map
     * @return
     */
    public List<AttrValue> getAttrValue(Map<String, Object> map) {
        Integer attrId = NumericUtil.nullToIntegerZero(map.get("attrId"));
        Integer communityId = NumericUtil.nullToIntegerZero(map.get("communityId"));
        String qryType = StrUtil.strnull(map.get("qryType"));
        List<AttrValue> ret = null;
        if (qryType == "" || qryType.equals("attrId")) {
            if (attrId != null) {
                String hql = "from AttrValue a where a.attrId = ? and a.statusCd = ? and (a.communityId is null or a.communityId =0 or a.communityId = ? )";
                List<Object> params = new ArrayList<Object>();
                params.add(attrId);
                params.add(BaseConstants.STATUS_VALID);
                params.add(communityId);
                ret = super.findListByHQLAndParams(hql, params, BaseConstants.QUERY_ROW_MAX);
            }
        } else if (qryType.equals("attrCd")) {
            String attrCd = StrUtil.strnull(map.get("attrCd"));
            if (attrCd != "") {
                List<AttrSpec> attrs = getAttr(attrCd);
                if (attrs != null && attrs.size() > 0) {
                    AttrSpec attr = attrs.get(0);
                    return attr.getAttrValue(communityId);
                }
            }

        }

        return ret;
    }
}
