package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.BuildingDao;
import com.wuye.entity.Building;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "buildingDao")
public class BuildingDaoImpl extends BaseDaoHibernate implements BuildingDao {
    public PageInfo getBuilding(Map<String, Object> map, int currentPage, int perPageNum) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from Building c where 1=1 ");
        String fuzzy = (String) map.get("fuzzy");
        String buildingName = (String) map.get("buildingName");
        Integer buildingId = (Integer) map.get("buildingId");
        List<Object> params = new ArrayList<Object>();
        if (!StrUtil.isNullOrEmpty(buildingName)) {
            if ("true".equals(fuzzy)) {
                hql.append(" and c.buildingName like ? ");
                params.add("%" + buildingName + "%");
            } else {
                hql.append(" and c.communityName = ? ");
                params.add(buildingName);
            }
        }
        if (!StrUtil.isNullOrEmpty(map.get("communityId"))) {
            hql.append(" and c.communityId = ? ");
            params.add(map.get("communityId"));
        }
        if (buildingId != null && !buildingId.equals(0)) {
            hql.append(" and c.buildingId = ? ");
            params.add(buildingId);
        }
        if (!StrUtil.isNullOrEmpty(map.get("ownerBuilding"))) {
            hql.append(" and c.ownerBuilding = ? ");
            params.add(map.get("ownerBuilding"));
        }
        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
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

    public PageInfo getSimpleBuildingBycommunity(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("select c from Building c,Community cm where 1=1 and c.ownerBuilding is null and c.community = cm and cm.communityId= ?  and c.statusCd = ? ");
        /*Community community = (Community) Community.getDao().getObject(Community.class, NumericUtil.nullToIntegerZero(map.get("communityId")));
		if (community == null){
			return null;
		}*/
        List<Object> params = new ArrayList<Object>();
        params.add(NumericUtil.nullToIntegerZero(map.get("communityId")));
        params.add(BaseConstants.STATUS_VALID);

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, 1, BaseConstants.QUERY_ROW_MAX);
        return pageInfo;
    }
}
