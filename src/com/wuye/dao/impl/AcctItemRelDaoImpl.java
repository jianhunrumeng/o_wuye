package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AcctItemRelDao;
import com.wuye.entity.AcctItemRel;
import com.wuye.entity.Building;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "acctItemRelDao")
public class AcctItemRelDaoImpl extends BaseDaoHibernate implements AcctItemRelDao {
    public PageInfo getAcctItemType(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from acctItemType c where 1=1 ");
        ArrayList params = new ArrayList();
        if (map.containsKey("qryType")) {
            //默认取大类，即parentAcctTypeId为空的
            if (StrUtil.isNullOrEmpty(map.get("qryType"))) {
                hql.append(" and c.parentAcctTypeId is null");
            } else {
                if (map.containsKey("parentAcctItemTypeId")
                        && !StrUtil.isNullOrEmpty(map.get("childAcctItemType"))) {
                    hql.append(" and c.parentAcctTypeId= ? ");
                    params.add(map.get("parentAcctItemTypeId"));
                }
            }
        }
        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, 0, 3000);
        return pageInfo;
    }

    public Building getOwnerBuildingEntt(Building building) {
        if (building.getOwnerBuilding() == null || building.getOwnerBuilding() == 0) {
            return null;
        }
        return (Building) super.getObject(Building.class, building.getOwnerBuilding());
    }

    public List<Building> getUnits(Building ownerBuilding) {
        if (ownerBuilding == null) {
            return null;
        }
        String hql = " from Building b where b.ownerBuilding = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(ownerBuilding.getId());
        return super.findListByHQLAndParams(hql, params);
    }

    public PageInfo queryAcctItemRels(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        boolean isActiveQuery = false;
        StringBuffer hql = new StringBuffer();
        hql.append("select c from AcctItemRel c where 1=1 and c.statusCd = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(BaseConstants.STATUS_VALID);
        //查询物业公司下面所有的
        if (map.containsKey("queryType")) {
            if ("queryByCompany".equals(map.get("queryType"))) {
                hql.append(" and c.classId= ? ");
                params.add(BaseConstants.CLASS_COMPANY);
                isActiveQuery = true;
            } else if ("queryByCommunity".equals(map.get("queryType"))) {
                hql.append(" and c.classId= ? ");
                params.add(BaseConstants.CLASS_COMMUNITY);
                isActiveQuery = true;
            } else if ("queryByBuilding".equals(map.get("queryType"))) {
                hql.append(" and c.classId= ? ");
                params.add(BaseConstants.CLASS_BUILDING);
                isActiveQuery = true;
            } else if ("queryByFloor".equals(map.get("queryType"))) {
                hql.append(" and c.classId= ? ");
                params.add(BaseConstants.CLASS_FLOOR);
                isActiveQuery = true;
            }
        }
        if (!isActiveQuery) {
            return null;
        }
        if (!StrUtil.isNullOrEmpty(map.get("objId"))) {
            if ("queryByFloor".equals(map.get("queryType"))) {
                hql.append(" and c.floor= ? ");
                params.add(map.get("objId"));
            } else {
                hql.append(" and c.objId= ? ");
                params.add(map.get("objId"));
            }

        }
        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, 1, BaseConstants.QUERY_ROW_MAX);
        List list = new ArrayList();
        if (!map.containsKey("convert")) {
            if (pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0) {
                for (int i = 0; i < pageInfo.getDataList().size(); i++) {
                    JSONObject jsObj = new JSONObject();
                    JSONObject destAcctItemRel = new JSONObject();
                    AcctItemRel acctItemRel = (AcctItemRel) pageInfo.getDataList().get(i);
                    EntityCopyUtil.populate(destAcctItemRel, acctItemRel,
                            new String[]{"price", "floor", "caculateMethodName", "objName", "caculateMethod"
                                    , "acctItemTypeName", "parentAcctItemTypeName", "acctItemRelId", "acctItemTypeId", "parentAcctTypeId"});
                    list.add(destAcctItemRel);
                }
            }
            pageInfo.setDataList(list);
        }
        return pageInfo;
    }
}
