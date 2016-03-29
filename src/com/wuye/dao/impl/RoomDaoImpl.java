package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.RoomDao;
import com.wuye.entity.Room;
import com.wuye.entity.RoomPartyRel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "roomDao")
public class RoomDaoImpl extends BaseDaoHibernate implements RoomDao {

    public PageInfo getRoom(Map<String, Object> map, int currentPage,
                            int perPageNum) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from Room c where 1=1 ");
        String fuzzy = (String) map.get("fuzzy");
        String buildingName = (String) map.get("buildingName");
        Integer buildingId = (Integer) map.get("buildingId");
        List<Object> params = new ArrayList<Object>();

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }

    public RoomPartyRel getOwnerRel(Room room) {
        StringBuffer hql = new StringBuffer();
        hql.append("select rp from RoomPartyRel rp");
        //hql.append(" where rp.room.roomId = r.roomId ");
        hql.append(" where rp.relType= ? ");
        hql.append(" and rp.statusCd=? ");
        hql.append(" and rp.room.roomId = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(BaseConstants.ROOM_PARTY_REL_TYPE_10);
        params.add(BaseConstants.STATUS_VALID);
        params.add(room.getRoomId());
        List<RoomPartyRel> rels = super.findListByHQLAndParams(hql.toString(), params);
        if (rels == null || rels.size() == 0) {
            return null;
        }
        return rels.get(0);
    }
}
