package com.wuye.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.date.JsonDateValueProcessor;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.Privilege;
import com.wuye.entity.Role;
import com.wuye.entity.RolePrivilege;
import com.wuye.entity.User;
import com.wuye.services.RoleServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleAction extends ActionSupport {
    private Logger log = Logger.getLogger(RoleAction.class);

    @Autowired
    private RoleServiceManager roleServiceManager;

    private String comingParams;
    private String suc_info = "";
    private int page;

    public String saveRole() {
        log.info("传入的参数:" + comingParams);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject jsonObj = JSONObject.fromObject(comingParams);
            if (jsonObj != null) {
                String roleName = jsonObj.getString("roleName");
                Role queryRole = roleServiceManager.getRole(roleName);
                inMap.put("role", jsonObj);
                //User user = (User) ActionContext.getContext().getSession().get("user");
                boolean isSave = false;
                if (queryRole != null) {
                    map.put("result", "isExit");
                    if (jsonObj.containsKey("roleId")
                            && queryRole.getRoleId().equals(jsonObj.getInt("roleId"))) {
                        map.remove("result");
                        isSave = true;
                    }
                } else {
                    isSave = true;
                }
                if (isSave) {
                    RetVO retVo = roleServiceManager.saveRoleAndRolePrivilege(inMap);
                    if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                        map.put("result", "true");
                        map.put("msg", "保存角色信息成功");
                    } else {
                        map.put("result", "false");
                        map.put("msg", "保存角色信息失败");
                    }
                }
                JSONObject returnJson = JSONObject.fromObject(map);
                suc_info = returnJson.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "保存角色信息失败");
            log.info(e.getMessage());
        }
        return "save_success";
    }

    public String getPageCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) ActionContext.getContext().getSession().get("user");
        map.put("userId", user.getUserId());
        if (comingParams != null && !"".equals(comingParams)) {
            map.put("roleName", comingParams);
        }
        int pageCount = roleServiceManager.getRoleCount(map);
        log.info("Role PageCount:" + pageCount);
        map = new HashMap<String, Object>();
        map.put("pageCount", pageCount);
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "role_page";
    }

    public String getRoleList() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //User user = (User) ActionContext.getContext().getSession().get("user");
            //map.put("userId", user.getUserId());
            if (comingParams != null && !"".equals(comingParams)) {
                map.put("roleName", comingParams);
            }
            if (StrUtil.isNullOrEmpty(page) || page < 1) {
                page = 1;
            }
            PageInfo pageInfo = roleServiceManager.qryRoleList(map, true, page, 10);
            if (pageInfo != null) {
                map.put("pageInfo", pageInfo);
            }
            map.put("result", "true");
            map.put("msg", "角色查询查询成功!");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));

        JSONObject jsontip = JSONObject.fromObject(map, jsonConfig);
        suc_info = jsontip.toString();
        return "role_list";
    }

    public String getRole() {
        int roleId = 0;
        if (comingParams != null && !"".equals(comingParams)) {
            roleId = Integer.parseInt(comingParams);
        }
        Role role = roleServiceManager.getRoleById(roleId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (role != null) {
            map.put("isExit", "yes");
            Map<String, Object> roleMap = new HashMap<String, Object>();
            roleMap.put("roleId", role.getRoleId());
            roleMap.put("roleName", role.getRoleName());
            roleMap.put("statusCd", role.getStatusCd());
            map.put("role", roleMap);
            List<Privilege> privilegeList = roleServiceManager.getPrivilegeListByRoleId(roleId);
            map.put("privilegeList", privilegeList);
        } else {
            map.put("isExit", "no");
        }

        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "role_obj";
    }

    public String getPrivilegePageCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) ActionContext.getContext().getSession().get("user");
        map.put("userId", user.getUserId());
        if (comingParams != null && !"".equals(comingParams)) {
            map.put("privilegeName", comingParams);
        }
        int pageCount = roleServiceManager.getPrivilegeCount(map);
        log.info("Role PageCount:" + pageCount);
        map = new HashMap<String, Object>();
        map.put("pageCount", pageCount);
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "privilege_page";
    }

    public String getPrivilegeList() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) ActionContext.getContext().getSession().get("user");
        map.put("userId", user.getUserId());
        if (comingParams != null && !"".equals(comingParams)) {
            map.put("privilegeName", comingParams);
        }
        map.put("page", page);
        List<Privilege> privilegeList = roleServiceManager.getPrivilegeList(map);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));
        JSONArray json = JSONArray.fromObject(privilegeList, jsonConfig);
        suc_info = json.toString();
        return "privilege_list";
    }

    public String removeRole() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (comingParams != null && !"".equals(comingParams)) {
            map.put("roleIdArray", JSONArray.fromObject(comingParams));
        }
        try {
            roleServiceManager.removeUserAuthAndRole(map);
            map = new HashMap<String, Object>();
            map.put("key", "success");
        } catch (Exception e) {
            map = new HashMap<String, Object>();
            map.put("key", e);
        }
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "remove_success";
    }

    public String joinInRole() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (comingParams != null && !"".equals(comingParams)) {
            JSONObject comJson = JSONObject.fromObject(comingParams);
            int roleId = comJson.getInt("roleId");
            Role role = new Role();
            role.setRoleId(roleId);
            JSONArray json = JSONArray.fromObject(comJson.get("chkValue"));
            List<RolePrivilege> rolePrivilegeList = new ArrayList<RolePrivilege>();
            for (int i = 0; i < json.size(); i++) {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setRole(role);
                Privilege privilege = new Privilege();
                privilege.setPrivilegeId(json.getInt(i));
                rolePrivilege.setPrivilege(privilege);
                rolePrivilegeList.add(rolePrivilege);
            }
            map.put("rolePrivilegeList", rolePrivilegeList);
        }

        try {
            roleServiceManager.joinInRole(map);
            map = new HashMap<String, Object>();
            map.put("key", "success");
        } catch (Exception e) {
            map = new HashMap<String, Object>();
            map.put("key", e);
        }
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "join_success";
    }

    public String quitRole() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (comingParams != null && !"".equals(comingParams)) {
            JSONObject comJson = JSONObject.fromObject(comingParams);
            int roleId = comJson.getInt("roleId");
            JSONArray json = JSONArray.fromObject(comJson.get("chkValue"));

            map.put("roleId", roleId);
            map.put("privilegeIdList", json);
        }
        try {
            roleServiceManager.quitRole(map);
            map = new HashMap<String, Object>();
            map.put("key", "success");
        } catch (Exception e) {
            map = new HashMap<String, Object>();
            map.put("key", e);
        }
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "quit_success";
    }

    public String updateRole() {
        log.info("传入的参数:" + comingParams);
        JSONObject jsonObj = JSONObject.fromObject(comingParams);
        Role role = (Role) JSONObject.toBean(jsonObj, Role.class);
        Role queryRole = roleServiceManager.getRole(role.getRoleName());
        Map<String, Object> map = new HashMap<String, Object>();
        if (queryRole != null && !queryRole.getRoleId().equals(role.getRoleId())) {
            map.put("key", "isExit");
        } else {
            try {
                roleServiceManager.updateRole(role);
                map.put("key", "success");
            } catch (Exception e) {
                map.put("key", e);
            }

        }
        JSONObject returnJson = JSONObject.fromObject(map);
        suc_info = returnJson.toString();
        return "save_success";
    }

    public String getComingParams() {
        return comingParams;
    }

    public void setComingParams(String comingParams) {
        this.comingParams = comingParams;
    }

    public String getSuc_info() {
        return suc_info;
    }

    public void setSuc_info(String sucInfo) {
        suc_info = sucInfo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
