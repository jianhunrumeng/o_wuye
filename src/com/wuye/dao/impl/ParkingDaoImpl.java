package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.ParkingDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "parkingDao")
public class ParkingDaoImpl extends BaseDaoHibernate implements ParkingDao {

    public PageInfo getParkingType(Map<String, Object> map, int currentPage,
                                   int perPageNum) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("from ParkingType c where 1=1 ")
                .append(" and c.statusCd = ? ");
        params.add(BaseConstants.STATUS_VALID);
        String fuzzy = (String) map.get("fuzzy");
        String parkingTypeName = (String) map.get("parkingTypeName");
        Integer parkingTypeId = (Integer) map.get("parkingTypeId");

        Integer communityId = (Integer) map.get("communityId");
        if (communityId != null && !communityId.equals(0)) {
            hql.append(" and c.communityId = ? ");
            params.add(communityId);
        }
        if (!StrUtil.isNullOrEmpty(parkingTypeName)) {
            if ("true".equals(fuzzy)) {
                hql.append(" and c.parkingTypeName like ? ");
                params.add("%" + parkingTypeName + "%");
            } else {
                hql.append(" and c.parkingTypeName = ? ");
                params.add(parkingTypeName);
            }
        }

        if (parkingTypeId != null && !parkingTypeId.equals(0)) {
            hql.append(" and c.parkingTypeId = ? ");
            params.add(parkingTypeId);
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }

    public PageInfo getParking(Map<String, Object> map, int currentPage,
                               int perPageNum) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();

        hql.append("from Parking c where 1=1 ")
                .append(" and c.statusCd = ? ");
        params.add(BaseConstants.STATUS_VALID);
        String fuzzy = (String) map.get("fuzzy");
        String parkingNbr = (String) map.get("parkingNbr");
        Integer parkingId = (Integer) map.get("parkingId");

        Integer communityId = (Integer) map.get("communityId");
        if (communityId != null && !communityId.equals(0)) {
            hql.append(" and c.communityId = ? ");
            params.add(communityId);
        }
        if (!StrUtil.isNullOrEmpty(parkingNbr)) {
            hql.append(" and c.parkingNbr = ? ");
            params.add(parkingNbr);
        }

        if (parkingId != null && !parkingId.equals(0)) {
            hql.append(" and c.parkingId = ? ");
            params.add(parkingId);
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }

}
