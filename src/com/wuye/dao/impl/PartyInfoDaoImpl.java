package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PartyInfoDao;
import com.wuye.entity.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "partyInfoDao")
public class PartyInfoDaoImpl extends BaseDaoHibernate implements PartyInfoDao {

    public PartyInfo getPartyInfo(String classId, Integer objId) {
        // TODO Auto-generated method stub
        String hql = "select p from PartyInfo p where p.classId = ? and p.objId = ? and p.statusCd = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(classId);
        params.add(objId);
        params.add(BaseConstants.STATUS_VALID);

        List<PartyInfo> ret = super.findListByHQLAndParams(hql, params);
        if (ret != null && ret.size() > 0) {
            return ret.get(0);
        }
        return null;
    }

    public BaseEntity getObj(String classId, Integer objId) {
        if (BaseConstants.CLASS_USER.equals(classId)) {
            return (User) this.getObject(User.class, objId);
        } else if (BaseConstants.CLASS_COMPANY.equals(classId)) {
            return (PropertyCompany) this.getObject(PropertyCompany.class, objId);
        } else if (BaseConstants.CLASS_COMMUNITY.equals(classId)) {
            return (Community) this.getObject(Community.class, objId);
        } else if (BaseConstants.CLASS_ORG.equals(classId)) {
            return (Organization) this.getObject(Organization.class, objId);
        } else if (BaseConstants.CLASS_BUILDING.equals(classId)) {
            return (Building) this.getObject(Building.class, objId);
        } else if (BaseConstants.CLASS_ROOM.equals(classId)) {
            return (Room) this.getObject(Room.class, objId);
        }
        return null;
    }

}
