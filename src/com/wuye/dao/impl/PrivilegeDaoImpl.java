package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.PrivilegeDao;
import com.wuye.entity.Privilege;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoHibernate implements PrivilegeDao {

    public List<Privilege> getPrivilegeList(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from ( ");
        sb.append(" select a.*  from privilege a, user_auth b ");
        sb.append(" where a.privilege_id = b.privilege_id ");
        sb.append(" and b.status_cd = '1000' ");
        sb.append(" and a.status_cd = '1000' ");
        if (map.containsKey("privilegeName") && map.get("privilegeName") != null) {
            sb.append(" and a.privilege_name like ? ");
            params.add("%" + map.get("privilegeName") + "%");
        }
        if (map.containsKey("userId") && map.get("userId") != null) {
            sb.append(" and b.user_id = ? ");
            params.add(map.get("userId"));
        }
        sb.append(" UNION ");
        sb.append(" select a.*   from privilege a, user_auth b, role c, role_privilege d ");
        sb.append(" where a.privilege_id = d.privilege_id ");
        sb.append(" and d.role_id = c.role_id ");
        sb.append(" and c.role_id = b.role_id ");
        sb.append(" and a.status_cd ='1000' ");
        sb.append(" and b.status_cd = '1000' ");
        sb.append(" and d.status_cd = '1000' ");
        if (map.containsKey("privilegeName") && map.get("privilegeName") != null) {
            sb.append(" and a.privilege_name like ? ");
            params.add("%" + map.get("privilegeName") + "%");
        }
        if (map.containsKey("userId") && map.get("userId") != null) {
            sb.append(" and b.user_id = ? ");
            params.add(map.get("userId"));
        }
        sb.append(" ) tn ");
        sb.append(" where 1=1 ");
        sb.append(" order by tn.privilege_id desc ");
        if (map.containsKey("listBegin") && map.containsKey("listEnd")) {
            sb.append(" limit ?, ? ");
            params.add(map.get("listBegin"));
            params.add(map.get("listEnd"));
        }
        List<Privilege> privilegeList = this.jdbcFindList(sb.toString(), Privilege.class, params);
        return privilegeList;
    }

    public List<Privilege> getPrivilegeListByRoleId(int roleId) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from ( ");
        sb.append(" select a.* from privilege a, role_privilege b ");
        sb.append(" where a.privilege_id = b.privilege_id ");
        sb.append(" and b.role_id = ? ) tn ");
        params.add(roleId);
        return this.jdbcFindList(sb.toString(), Privilege.class, params);
    }

    public PageInfo qryPrivilegeList(Map<String, Object> map, boolean fuzzy, int currentPage,
                                     int perPageNum) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Privilege c where 1=1 ");
        List<String> params = new ArrayList<String>();
        if (map != null && map.size() > 0) {
            if (map.containsKey("privilegeName")) {
                if (fuzzy) {
                    hql.append(" and c.privilegeName like ?");
                    params.add("%" + map.get("privilegeName") + "%");
                } else {
                    hql.append(" and c.privilegeName like =?");
                    params.add(map.get("privilegeName") + "");
                }
            }

            if (map.containsKey("privilegeId")) {
                hql.append(" and c.privilegeId =?");
                params.add(map.get("privilegeId") + "");
            }
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }

    public void add(JSONObject srcPrivilege) {
        Privilege privilege = new Privilege();

        EntityCopyUtil.populate(privilege, srcPrivilege, null);
        Date date = new Date();
        privilege.setCreateDate(date);
        privilege.setUpdateDate(date);
        privilege.save();
    }

    public void update(JSONObject srcPrivilege) {
        Privilege privilege = new Privilege();
        EntityCopyUtil.populate(privilege, srcPrivilege, null);
        privilege.setId(srcPrivilege.getInt("privilegeId"));
        Date date = new Date();
        privilege.setUpdateDate(date);
        privilege.save();
    }

    public List<Privilege> getParentPrivilege(Map<String, Object> map) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select a.* from privilege a where a.parent_privilege_id is null");
        if (map.containsKey("privilegeType")) {
            sql.append(" and privilege_type = ?");
            params.add(map.get("privilegeType"));
        }
        List<Privilege> privileges = this.jdbcFindList(sql.toString(), Privilege.class, params);
        return privileges;
    }

    public List<Privilege> qryUserMenuPrivilege(Map<String, Object> map) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct p.* from user_auth ua,role_privilege rp,privilege p where ua.role_id = rp.role_id");
        sql.append(" and rp.privilege_id = p.privilege_id");
        if (map.containsKey("privilegeType")) {
            sql.append(" and p.privilege_type = ?");
            params.add(map.get("privilegeType"));
        }
        if (map.containsKey("userId")) {
            sql.append(" and ua.user_id = ?");
            params.add(map.get("userId"));
        }

        if (map.containsKey("isParent")) {
            Object obj = map.get("isParent");
            if ("0".equals(StrUtil.strnull(obj))) {
                sql.append(" and p.parent_privilege_id is null");
            } else {
                sql.append(" and p.parent_privilege_id = ?");
                params.add(map.get("isParent"));
            }
        }

        List<Privilege> privileges = this.jdbcFindList(sql.toString(), Privilege.class, params);
        return privileges;
    }


}
