package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PrivilegeDao;
import com.wuye.dao.RoleDao;
import com.wuye.dao.UserAuthDao;
import com.wuye.entity.Privilege;
import com.wuye.entity.Role;
import com.wuye.entity.RolePrivilege;
import com.wuye.entity.User;
import com.wuye.services.RoleServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roleServiceManager")
public class RoleServiceManagerImpl extends BaseManagerImpl implements RoleServiceManager {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private PrivilegeDao privilegeDao;

    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    public Role getRole(String roleName) {
        return roleDao.getRole(roleName);
    }

    public void saveRoleAndUserAuthAndPrivilege(Role role, User user, Privilege privilege) {
        if (role != null) {
            roleDao.saveRole(role);
        }
        /*if (role != null && role.getRoleId() != null && user != null) {
			UserAuth userAuth = new UserAuth();
			userAuth.setRole(role);
			userAuth.setUser(user);
			userAuth.setStatusCd("1000");
			userAuthDao.saveUserAuth(userAuth);
		}*/
    }

    public int getRoleCount(Map<String, Object> map) {
        List<Role> roleList = roleDao.getRoleList(map);
        int recordCount = 0;
        int pageCount = 0;
        if (roleList != null) {
            recordCount = roleList.size();
            if (recordCount % BaseConstants.DEFAULT_PAGE_PER_COUNT == 0 && recordCount != 0) {
                pageCount = recordCount / BaseConstants.DEFAULT_PAGE_PER_COUNT;
            } else {
                pageCount = recordCount / BaseConstants.DEFAULT_PAGE_PER_COUNT + 1;
            }
        }
        return pageCount;
    }

    public List<Role> getRoleList(Map<String, Object> map) {
        int listBegin, listEnd;
        int page = 0;
        if (map.containsKey("page")) {
            page = (Integer) map.get("page");
        }
        if (page == 0) {
            listBegin = 0;
            listEnd = BaseConstants.DEFAULT_PAGE_PER_COUNT;
        } else {
            listBegin = BaseConstants.DEFAULT_PAGE_PER_COUNT * (page - 1);
            listEnd = BaseConstants.DEFAULT_PAGE_PER_COUNT * (page);
        }
        map.put("listBegin", listBegin);
        map.put("listEnd", listEnd);

        return roleDao.getRoleList(map);
    }

    public Role getRoleById(int roleId) {
        Role role = (Role) super.getObject(Role.class, roleId);
        return role;
    }

    public int getPrivilegeCount(Map<String, Object> map) {
        List<Privilege> privilegeList = privilegeDao.getPrivilegeList(map);
        int recordCount = 0;
        int pageCount = 0;
        if (privilegeList != null) {
            recordCount = privilegeList.size();
            if (recordCount % BaseConstants.DEFAULT_PAGE_PER_COUNT == 0 && recordCount != 0) {
                pageCount = recordCount / BaseConstants.DEFAULT_PAGE_PER_COUNT;
            } else {
                pageCount = recordCount / BaseConstants.DEFAULT_PAGE_PER_COUNT + 1;
            }
        }
        return pageCount;
    }

    public List<Privilege> getPrivilegeList(Map<String, Object> map) {
        int listBegin, listEnd;
        int page = 0;
        if (map.containsKey("page")) {
            page = (Integer) map.get("page");
        }
        if (page == 0) {
            listBegin = 0;
            listEnd = BaseConstants.DEFAULT_PAGE_PER_COUNT;
        } else {
            listBegin = BaseConstants.DEFAULT_PAGE_PER_COUNT * (page - 1);
            listEnd = BaseConstants.DEFAULT_PAGE_PER_COUNT * (page);
        }
        map.put("listBegin", listBegin);
        map.put("listEnd", listEnd);

        return privilegeDao.getPrivilegeList(map);
    }

    public void removeUserAuthAndRole(Map<String, Object> map) {
        if (map.containsKey("roleIdArray")) {
            JSONArray json = (JSONArray) map.get("roleIdArray");
            for (int i = 0; i < json.size(); i++) {
                int roleId = json.getInt(i);
                userAuthDao.removeUserAuthByRoleId(roleId);
                roleDao.removeRole(roleId);
                roleDao.removeRolePrivilege(roleId);
            }
        }
    }

    public List<Privilege> getPrivilegeListByRoleId(int roleId) {
        return privilegeDao.getPrivilegeListByRoleId(roleId);
    }

    public void joinInRole(Map<String, Object> map) {
        if (map.containsKey("rolePrivilegeList")) {
            List<RolePrivilege> json = (List<RolePrivilege>) map.get("rolePrivilegeList");
            for (RolePrivilege rolePrivilege : json) {
                roleDao.jsonInRole(rolePrivilege);
            }
        }
    }

    public void quitRole(Map<String, Object> map) {
        if (map.containsKey("roleId") && map.containsKey("privilegeIdList")) {
            int roleId = (Integer) map.get("roleId");
            JSONArray json = (JSONArray) map.get("privilegeIdList");
            for (int i = 0; i < json.size(); i++) {
                int privilegeId = json.getInt(i);
                roleDao.quitRole(roleId, privilegeId);
            }
        }
    }

    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    public List<Role> getRoleListByUserId(int userId) {
        return roleDao.getRoleListByUserId(userId);
    }

    public RetVO saveRoleAndRolePrivilege(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "权限保存成功!");
        JSONObject object = (JSONObject) map.get("role");
        JSONArray privileges = null;
        if (object.containsKey("rolePrivileges")) {
            privileges = object.getJSONArray("rolePrivileges");
        }

        if (!object.containsKey("roleId")) {
            Role role = new Role();
            role.setStatusCd(object.getString("statusCd"));
            role.setRoleName(object.getString("roleName"));
            role.save();
            if (privileges != null && privileges.size() > 0) {
                for (int i = 0; i < privileges.size(); i++) {
                    JSONObject srcPri = privileges.getJSONObject(i);
                    RolePrivilege rolePrivilege = new RolePrivilege();
                    rolePrivilege.setRoleId(role.getId());
                    rolePrivilege.setPrivilegeId(srcPri.getInt("privilegeId"));
                    rolePrivilege.save();
                }
            }
        } else {
            Role role = new Role();
            role.setId(object.getInt("roleId"));
            role.setStatusCd(object.getString("statusCd"));
            role.setRoleName(object.getString("roleName"));
            role.save();
            roleDao.removeRolePrivilege(role.getId());
            if (privileges != null && privileges.size() > 0) {
                for (int i = 0; i < privileges.size(); i++) {
                    JSONObject srcPri = privileges.getJSONObject(i);
                    RolePrivilege rolePrivilege = new RolePrivilege();
                    rolePrivilege.setRoleId(role.getId());
                    rolePrivilege.setPrivilegeId(srcPri.getInt("privilegeId"));
                    rolePrivilege.save();
                }
            }
        }

        return retVo;
    }

    public PageInfo qryRoleList(Map<String, Object> map, boolean fuzzy, int currentPage,
                                int perPageNum) {
        return roleDao.qryRoleList(map, fuzzy, currentPage, perPageNum);
    }


}
