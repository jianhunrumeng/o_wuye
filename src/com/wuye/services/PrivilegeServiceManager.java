package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.MenuVo;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.Privilege;

import java.util.List;
import java.util.Map;

public interface PrivilegeServiceManager extends BaseManager {
    PageInfo qryPrivilegeList(Map<String, Object> map, boolean fuzzy, final int currentPage, final int perPageNum);

    RetVO save(Map<String, Object> map) throws Exception;

    RetVO del(Map<String, Object> map) throws Exception;

    RetVO getParentPrivilege(Map<String, Object> map);

    List<Privilege> qryRolePrivilegeByRoleId(int roleId);

    /**
     * 获取登录用户的菜单.
     *
     * @param map
     * @return
     * @author Luxb
     * 2015-12-27 Luxb
     */
    List<MenuVo> qryUserMenuPrivilege(Map<String, Object> map);
}
