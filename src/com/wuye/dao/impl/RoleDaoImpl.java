package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.RoleDao;
import com.wuye.entity.Role;
import com.wuye.entity.RolePrivilege;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoHibernate implements RoleDao {

    public void saveRole(Role role) {
        this.addObject(role);
    }

    public Role getRole(String roleName) {
        String hql = " select a.* from role a where a.role_name = ? ";
        List params = new ArrayList();
        params.add(roleName);
        List<Role> roleList = this.jdbcFindList(hql, Role.class, params);
        if (roleList != null && roleList.size() > 0) {
            return roleList.get(0);
        }
        return null;
    }

    public List<Role> getRoleList(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from ( ");
        sb.append(" select a.* from role a, user_auth b where a.role_id = b.role_id ");
        if (map.containsKey("roleName") && !"".equals(map.get("roleName"))) {
            sb.append(" and a.role_name like ? ");
            params.add("%" + map.get("roleName") + "%");
        }
        sb.append(" and b.user_id = ? ");
        params.add(map.get("userId"));
        sb.append(" order by a.role_id desc ");
        if (map.containsKey("listBegin") && map.containsKey("listEnd")) {
            sb.append(" limit ?, ? ");
            params.add(map.get("listBegin"));
            params.add(map.get("listEnd"));
        }
        sb.append(" ) tn ");
        return this.jdbcFindList(sb.toString(), Role.class, params);
    }

    public void removeRole(int roleId) {
        this.removeObject(Role.class, roleId);
    }

    public void jsonInRole(RolePrivilege rolePrivilege) {
        this.addObject(rolePrivilege);
    }

    public void quitRole(int roleId, int privilegeId) {
        String hql = " delete from role_privilege where role_id = ? and privilege_id = ? ";
        List params = new ArrayList();
        params.add(roleId);
        params.add(privilegeId);
        this.executeSql(hql, params);
    }

    public void updateRole(Role role) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" update role set ");
        if (role.getRoleName() != null) {
            sb.append(" role_name = ? , ");
            params.add(role.getRoleName());
        }
        if (role.getStatusCd() != null) {
            sb.append(" status_cd = ? ");
            params.add(role.getStatusCd());
        }

        sb.append(" where role_id = ? ");
        params.add(role.getRoleId());
        this.executeSql(sb.toString(), params);
    }

    public List<Role> getRoleListByUserId(int userId) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from ( ");
        sb.append(" select a.* from role a, user_auth b where a.role_id = b.role_id ");
        sb.append(" and a.status_cd = ? ");
        params.add("1000");
        sb.append(" and b.status_cd = ? ");
        params.add("1000");
        sb.append(" and b.user_id = ? ) tn ");
        params.add(userId);
        List<Role> roleList = this.jdbcFindList(sb.toString(), Role.class, params);
        return roleList;
    }

    /**
     * {@inheritDoc}
     *
     * @author Luxb
     * 2015-12-17 Luxb
     * @see com.wuye.dao.RoleDao#removeRolePrivilege(int)
     */
    public void removeRolePrivilege(int roleId) {
        StringBuffer sql = new StringBuffer();
        List params = new ArrayList();
        sql.append(" delete from role_privilege where role_id=?");
        params.add(roleId);
        this.executeSql(sql.toString(), params);
    }

    public PageInfo qryRoleList(Map<String, Object> map, boolean fuzzy, int currentPage,
                                int perPageNum) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Role c where 1=1 ");
        List<String> params = new ArrayList<String>();
        if (map != null && map.size() > 0) {
            if (map.containsKey("roleName")) {
                if (fuzzy) {
                    hql.append(" and c.roleName like ?");
                    params.add("%" + map.get("roleName") + "%");
                } else {
                    hql.append(" and c.roleName like =?");
                    params.add(map.get("roleName") + "");
                }
            }

            if (map.containsKey("roleId")) {
                hql.append(" and c.roleId =?");
                params.add(map.get("roleId") + "");
            }
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }


}
