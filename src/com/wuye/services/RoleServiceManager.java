package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.Privilege;
import com.wuye.entity.Role;
import com.wuye.entity.User;

import java.util.List;
import java.util.Map;

public interface RoleServiceManager extends BaseManager {
    void saveRole(Role role);

    Role getRole(String roleName);

    void saveRoleAndUserAuthAndPrivilege(Role role, User user, Privilege privilege);

    int getRoleCount(Map<String, Object> map);

    List<Role> getRoleList(Map<String, Object> map);

    Role getRoleById(int roleId);

    int getPrivilegeCount(Map<String, Object> map);

    List<Privilege> getPrivilegeList(Map<String, Object> map);

    void removeUserAuthAndRole(Map<String, Object> map);

    List<Privilege> getPrivilegeListByRoleId(int roleId);

    void joinInRole(Map<String, Object> map);

    void quitRole(Map<String, Object> map);

    void updateRole(Role role);

    List<Role> getRoleListByUserId(int userId);

    RetVO saveRoleAndRolePrivilege(Map<String, Object> map);

    PageInfo qryRoleList(Map<String, Object> map, boolean fuzzy, final int currentPage, final int perPageNum);

}
