package com.wuye.services.impl;

import com.wuye.common.dao.Dao;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.MenuVo;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PrivilegeDao;
import com.wuye.entity.BaseEntity;
import com.wuye.entity.Privilege;
import com.wuye.services.PrivilegeServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("privilegeServiceManager")
public class PrivilegeServiceManagerImpl implements PrivilegeServiceManager {

    @Autowired
    private PrivilegeDao privilgeDao;

    public PageInfo qryPrivilegeList(Map<String, Object> map, boolean fuzzy, int currentPage,
                                     int perPageNum) {
        return privilgeDao.qryPrivilegeList(map, fuzzy, currentPage, perPageNum);
    }

    public Object getObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List getObjects(Class clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    public void removeObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub

    }

    public void save(BaseEntity baseEntity) {
        // TODO Auto-generated method stub

    }

    public void saveObject(Object o) {
        // TODO Auto-generated method stub

    }

    public void setDao(Dao dao) {
        // TODO Auto-generated method stub

    }

    public void updateObjectInvalid(Object o) {
        // TODO Auto-generated method stub

    }

    public RetVO save(Map<String, Object> map) throws Exception {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "权限保存成功!");

        //String type = (String) map.get("type");
        JSONArray privileges = (JSONArray) map.get("privilege");
        if (privileges != null && privileges.size() > 0) {
            for (int i = 0; i < privileges.size(); i++) {
                JSONObject srcPrivilege = privileges.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcPrivilege.get("privilegeId"))) {
                    // 新增
                    privilgeDao.add(srcPrivilege);
                } else {
                    // 更改
                    privilgeDao.update(srcPrivilege);
                }
            }
        }

        return retVo;
    }

    public RetVO del(Map<String, Object> map) throws Exception {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "权限删除成功!");

        JSONArray privileges = (JSONArray) map.get("privilegeIdArray");
        if (privileges != null && privileges.size() > 0) {
            for (int i = 0; i < privileges.size(); i++) {
                int privilegeId = privileges.getInt(i);
                privilgeDao.removeObject(Privilege.class, privilegeId);
            }
        }

        return retVo;
    }

    public RetVO getParentPrivilege(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "权限获取成功!");
        List<Privilege> privileges = privilgeDao.getParentPrivilege(map);
        retVo.setDataList(privileges);
        return retVo;
    }

    public List<Privilege> qryRolePrivilegeByRoleId(int roleId) {
        return privilgeDao.getPrivilegeListByRoleId(roleId);
    }

    public List<MenuVo> qryUserMenuPrivilege(Map<String, Object> map) {
        List<MenuVo> voList = new ArrayList<MenuVo>();
        List<Privilege> list = privilgeDao.qryUserMenuPrivilege(map);
        if (list != null && list.size() > 0) {
            for (Privilege privilege : list) {
                voList.add(dealUserMenu(map, privilege));
            }
        }
        return voList;
    }

    /**
     * .
     *
     * @return
     * @author Luxb
     * 2015-12-27 Luxb
     */
    public MenuVo dealUserMenu(Map<String, Object> map, Privilege privilege) {
        MenuVo vo = new MenuVo();
        vo.setMenuId(privilege.getId());
        vo.setMenuName(privilege.getPrivilegeName());
        vo.setMenuPath(privilege.getPath());
        map.put("isParent", privilege.getId());
        List<Privilege> list = privilgeDao.qryUserMenuPrivilege(map);
        if (list != null && list.size() > 0) {
            for (Privilege privilege2 : list) {
                vo.getChildMenus().add(dealUserMenu(map, privilege2));
            }
        }
        return vo;
    }

}
