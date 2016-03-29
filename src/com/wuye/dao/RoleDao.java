package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;
import com.wuye.entity.Role;
import com.wuye.entity.RolePrivilege;

import java.util.List;
import java.util.Map;

public interface RoleDao extends Dao {
    /**
     * 保存角色信息
     *
     * @param role
     * @return
     */
    void saveRole(Role role);

    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName
     * @return
     */
    Role getRole(String roleName);

    /**
     * 角色的列表
     *
     * @param map
     * @return
     */
    List<Role> getRoleList(Map<String, Object> map);

    /**
     * 删除Role
     *
     * @param roleId
     */
    void removeRole(int roleId);

    /**
     * 权限加入角色
     *
     * @param rolePrivilege
     */
    void jsonInRole(RolePrivilege rolePrivilege);

    /**
     * 权限退出角色
     *
     * @param rolePrivilege
     */
    void quitRole(int roleId, int privilegeId);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据UserId获取角色信息
     *
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(int userId);

    void removeRolePrivilege(int roleId);

    PageInfo qryRoleList(Map<String, Object> map, boolean fuzzy, final int currentPage, final int perPageNum);
}

