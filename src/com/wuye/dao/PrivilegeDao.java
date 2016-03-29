package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;
import com.wuye.entity.Privilege;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface PrivilegeDao extends Dao {
    /**
     * 获取权限列表
     *
     * @param map
     * @return
     */
    List<Privilege> getPrivilegeList(Map<String, Object> map);

    /**
     * 根据roleId获取其下的权限信息
     *
     * @param roleId
     * @return
     */
    List<Privilege> getPrivilegeListByRoleId(int roleId);

    /**
     * .
     *
     * @param map
     * @param fuzzy
     * @param currentPage
     * @param perPageNum
     * @return
     * @author Luxb
     * 2015-12-16 Luxb
     */
    PageInfo qryPrivilegeList(Map<String, Object> map, boolean fuzzy, final int currentPage, final int perPageNum);

    /**
     * .
     *
     * @param srcPrivilege
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    void add(JSONObject srcPrivilege) throws Exception;

    /**
     * .
     *
     * @param srcPrivilege
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    void update(JSONObject srcPrivilege) throws Exception;

    /**
     * .
     *
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    List<Privilege> getParentPrivilege(Map<String, Object> map);

    /**
     * 获取用户的菜单权限.
     *
     * @param map
     * @return
     * @author Luxb
     * 2015-12-27 Luxb
     */
    List<Privilege> qryUserMenuPrivilege(Map<String, Object> map);
}
