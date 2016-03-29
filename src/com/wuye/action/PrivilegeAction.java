package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.date.JsonDateValueProcessor;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.Privilege;
import com.wuye.services.PrivilegeServiceManager;
import com.wuye.services.vo.TreeNodeVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限action.
 *
 * @author Luxb
 * @version Revision 1.0.0
 * @see:
 * @创建日期：2015-12-14
 * @功能说明：
 */
public class PrivilegeAction extends ActionSupport {
    /**
     * .
     */
    private static final long serialVersionUID = 4351082496645104334L;

    private Logger log = Logger.getLogger(PrivilegeAction.class);

    @Autowired
    private PrivilegeServiceManager privilegeServiceManager;

    private String jsondata;

    private String comingParams;

    private String suc_info = "";

    private int page;

    /**
     * 权限获取.
     *
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    public String qryPrivilegeList() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //boolean isLogin=false;

            if (!StrUtil.isNullOrEmpty(jsondata)) {
                JSONObject json = JSONObject.fromObject(jsondata);
                //有传isQueryOne代表查询单独一个
                if (!StrUtil.isNullOrEmpty(json.get("isQueryOne"))
                        && "yes".equals(json.get("isQueryOne").toString())
                        && !StrUtil.isNullOrEmpty(json.get("privilegeId"))) {
                    map.put("privilegeId", json.get("privilegeId"));
                }
                if (!StrUtil.isNullOrEmpty(json.get("privilegeName"))) {
                    map.put("privilegeName", json.get("privilegeName"));
                }
            }
            if (StrUtil.isNullOrEmpty(page) || page < 1) {
                page = 1;
            }

            PageInfo pageInfo = privilegeServiceManager.qryPrivilegeList(map, true, page, 10);
            if (pageInfo != null) {
                map.put("pageInfo", pageInfo);
            }

            map.put("result", "true");
            map.put("msg", "权限查询查询成功!");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));

        JSONObject jsontip = JSONObject.fromObject(map, jsonConfig);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 权限保存.
     *
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    public String savePrivilege() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(jsondata);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("privilege", jsonArray);

                RetVO retVo = privilegeServiceManager.save(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存权限信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存权限信息失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "保存权限信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 权限删除.
     *
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    public String removePrivilege() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            if (jsondata != null) {
                inMap.put("privilegeIdArray", JSONArray.fromObject(jsondata));

                RetVO retVo = privilegeServiceManager.del(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "权限删除成功！");
                } else {
                    map.put("result", "false");
                    map.put("msg", "权限删除失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "权限删除失败！");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 获取上级权限.
     *
     * @return
     * @author Luxb
     * 2015-12-14 Luxb
     */
    public String getParentPrivilege() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            if (!StrUtil.isNullOrEmpty(jsondata)) {
                inMap.put("privilegeType", jsondata);
            }

            RetVO retVo = privilegeServiceManager.getParentPrivilege(inMap);
            if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                map.put("data", retVo.getDataList());
                map.put("result", "true");
                map.put("msg", "权限获取成功！");
            } else {
                map.put("result", "false");
                map.put("msg", "权限获取失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "权限获取失败！");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 获取权限树.
     *
     * @return
     * @author Luxb
     * 2015-12-17 Luxb
     */
    public String getPrivilegeTree() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            if (!StrUtil.isNullOrEmpty(jsondata)) {
                inMap.put("privilegeType", jsondata);
            }

            if (!StrUtil.isNullOrEmpty(comingParams)) {
                inMap.put("roleId", comingParams);
            }
            RetVO retVo = privilegeServiceManager.getParentPrivilege(inMap);
            if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                List<Privilege> pList = retVo.getDataList();
                if (pList != null && pList.size() > 0) {
                    List result = new ArrayList();
                    Map<Integer, Object> tmp = new HashMap<Integer, Object>();
                    if (!StrUtil.isNullOrEmpty(comingParams)) {
                        List<Privilege> list = privilegeServiceManager.qryRolePrivilegeByRoleId(NumericUtil.toInt(comingParams));
                        if (list != null && list.size() > 0) {
                            for (Privilege privilege : list) {
                                tmp.put(privilege.getId(), privilege);
                            }
                        }
                    }
                    dealPrivilegeTree(null, pList, result, tmp);
                    map.put("data", result);
                    map.put("result", "true");
                    map.put("msg", "权限获取成功！");
                }
            } else {
                map.put("result", "false");
                map.put("msg", "权限获取失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "权限获取失败！");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public List dealPrivilegeTree(TreeNodeVo pVo, List<Privilege> pList, List result, Map<Integer, Object> existMap) {
        for (Privilege privilege : pList) {
            TreeNodeVo vo = new TreeNodeVo();
            vo.setId(privilege.getId());
            if (StrUtil.isNullOrEmpty(privilege.getParentPrivilegeId())) {
                vo.setpId(-1);
            } else {
                vo.setpId(privilege.getParentPrivilegeId());
                if (existMap.containsKey(privilege.getId())) {
                    vo.setChecked(true);
                    if (pVo != null) {
                        pVo.setChecked(true);
                    }
                }
            }
            vo.setName(privilege.getPrivilegeName());
            result.add(vo);
            if (privilege.getChildPrivileges() != null && privilege.getChildPrivileges().size() > 0) {
                dealPrivilegeTree(vo, privilege.getChildPrivileges(), result, existMap);
            }
        }

        return result;
    }

    public String getJsondata() {
        return this.jsondata;
    }

    public String getComingParams() {
        return this.comingParams;
    }

    public String getSuc_info() {
        return this.suc_info;
    }

    public int getPage() {
        return this.page;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public void setComingParams(String comingParams) {
        this.comingParams = comingParams;
    }

    public void setSuc_info(String suc_info) {
        this.suc_info = suc_info;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
