package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.PropertyCompany;
import com.wuye.services.PropertyCompanyServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyCompanyAction extends ActionSupport {
    private Logger log = Logger.getLogger(PropertyCompanyAction.class);
    @Autowired
    private PropertyCompanyServiceManager propertyCompanyServiceManager;
    //	@Autowired
//	private Manager baseManager;
    private String jsondata = "";
    private String returnInfo = "";
    private int page;

    public String addPropertyCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(jsondata);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                Map<String, Object> inMap = new HashMap<String, Object>();
                inMap.put("propertyCompany", jsonArray);
                propertyCompanyServiceManager.savePreopertyCompany(inMap);
            }
            map.put("result", "true");
            map.put("msg", "物业公司信息保存成功!");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        returnInfo = jsontip.toString();
        return "save_property";

    }

    public String getPropertyCompanyList() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean isLogin = false;

            if (!StrUtil.isNullOrEmpty(jsondata)) {
                JSONObject json = JSONObject.fromObject(jsondata);
                //有传isQueryOne代表查询单独一个
                if (!StrUtil.isNullOrEmpty(json.get("isQueryOne"))
                        && "yes".equals(json.get("isQueryOne").toString())
                        && !StrUtil.isNullOrEmpty(json.get("companyId"))) {
                    map.put("companyId", json.get("companyId"));
                }
                //界面展示模糊查询的时候使用
                if (!StrUtil.isNullOrEmpty(json.get("propertyCompanyName"))) {
                    map.put("propertyCompanyName", json.get("propertyCompanyName"));
                }
                if (StrUtil.isNullOrEmpty(page) || page < 1) {
                    page = 1;
                }
            }
            PageInfo pageInfo = propertyCompanyServiceManager.getPreopertyCompanyList(
                    map, page, BaseConstants.DEFAULT_PAGE_PER_COUNT);
            if (pageInfo != null) {
                map.put("pageInfo", pageInfo);
            }
            map.put("result", "true");
            map.put("msg", "物业公司查询成功!");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
//		JsonConfig jsonConfig = SpringUtil.getBean("jsonConfig");

        //jsonConfig.setJsonPropertyFilter(new InvisibleFilter(true,"no"));
        //jsonConfig.setJsonPropertyFilter(new HibernatePropertyFilter());
        //jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
//		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));  
//		JSONArray json = JSONArray.fromObject(pageInfo.getDataList(), jsonConfig);
//		JSONArray json = JSONArray.fromObject(map, jsonConfig);
        JSONObject jsontip = JSONObject.fromObject(map);
        returnInfo = jsontip.toString();
        return "list_property";

    }

    /**
     * 删除物业公司
     */
    public String removePropertyCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!StrUtil.isNullOrEmpty(jsondata)) {
                map.put("companyIdArray", JSONArray.fromObject(jsondata));
                propertyCompanyServiceManager.removePropertyCompany(map);
                map.put("key", "success");
            }
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        returnInfo = jsontip.toString();
        return "list_property";
    }

    public String getPropertyCompanyFiveList() {
        if (!StrUtil.isNullOrEmpty(jsondata)) {
            JSONArray json = new JSONArray();
            List<PropertyCompany> pcList = propertyCompanyServiceManager.queryPropertyCompanyByName(jsondata);
            for (PropertyCompany pc : pcList) {
                Map<String, Object> m = new HashMap<String, Object>();
                int pcId = pc.getId();
                String pcName = pc.getCompanyName();
                m.put("companyId", pcId);
                m.put("companyName", pcName);
                json.add(m);
            }
            returnInfo = json.toString();
            log.info("five =" + returnInfo);
        }
        return "list_property";
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
