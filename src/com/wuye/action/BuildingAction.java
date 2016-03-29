package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.date.JsonDateValueProcessor;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.services.BuildingServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BuildingAction extends ActionSupport {
    private Logger log = Logger.getLogger(BuildingAction.class);
    @Autowired
    private BuildingServiceManager buildingServiceManager;

    private int page;
    private String inParma;
    private String suc_info;

    public String getBuildingCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageCount = buildingServiceManager.getBuildingCount(map);
        log.info("小区数量:" + pageCount);
        /*map = new HashMap<String, Object>();
		map.put("pageCount", pageCount);
		JSONObject json = JSONObject.fromObject(map);
		suc_info = json.toString();*/
        StringBuffer ret = new StringBuffer();
        ret.append("{\"pageCount\"").append(":")
                .append("\"").append(pageCount).append("\"}");
        suc_info = ret.toString();
        return SUCCESS;
    }

    public String getBuildingList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", 1);
        if (!StrUtil.isNullOrEmpty(inParma)) {
            map.put("communityName", inParma);
        }
        PageInfo pageInfo = buildingServiceManager.getBuildingByName(inParma, true, Integer.valueOf(page), 12);
        //JsonConfig jsonConfig = new JsonConfig();
        JsonConfig jsonConfig = SpringUtil.getBean("jsonConfig");

        //jsonConfig.setJsonPropertyFilter(new InvisibleFilter(true,"no"));
        //jsonConfig.setJsonPropertyFilter(new HibernatePropertyFilter());
        //jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        try {
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));
            JSONArray json = JSONArray.fromObject(pageInfo.getDataList(), jsonConfig);
            suc_info = json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getBuilding() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json.get("buildingId"))) {
                paramMap.put("buildingId", NumericUtil.nullToIntegerZero(json.get("buildingId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("buildingName"))) {
                paramMap.put("buildingName", StrUtil.strnull(json.get("buildingName")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("ownerBuilding"))) {
                paramMap.put("ownerBuilding", NumericUtil.nullToIntegerZero(json.get("ownerBuilding")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("fuzzy"))) {
                paramMap.put("fuzzy", StrUtil.strnull(json.get("fuzzy")));
            }
            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo = null;
            if (type.equals("getSimpleBuildingBycommunity")) {
                retvo = buildingServiceManager.getSimpleBuildingBycommunity(paramMap);
            } else {
                retvo = buildingServiceManager.getBuilding(paramMap, NumericUtil.nullToZero(page), 12);
            }

            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    JSONArray jsArray = (JSONArray) retvo.getObject();
                    suc_info = jsArray.toString();
                    retMap.put("data", jsArray);
                }
                retMap.put("result", "true");
                retMap.put("msg", "小区信息查询成功!");

            } else {
                retMap.put("result", "false");
                retMap.put("msg", "小区信息查询失败!");
            }

            //JsonConfig jsonConfig = new JsonConfig();


        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("result", "false");
            retMap.put("msg", "小区信息查询失败!");
        }
		/*JsonConfig jsonConfig = SpringUtil.getBean("jsonConfig");
		
		//jsonConfig.setJsonPropertyFilter(new InvisibleFilter(true,"no")); 
		//jsonConfig.setJsonPropertyFilter(new HibernatePropertyFilter()); 
		//jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));  
		*/
        JSONObject jsonArr = JSONObject.fromObject(retMap);
        suc_info = jsonArr.toString();
        return SUCCESS;
    }

    /*public String addBuilding(){
        Map<String,Object> map = new HashMap<String, Object>();

        try {
            JSONObject json =JSONObject.fromObject(inParma);
            if (json != null){
                Map<String,Object> inMap = new HashMap<String, Object>();
                inMap.put("building", json);
                inMap.put("type", BaseConstants.ACTION_ADD);
            }

            map.put("result", "true");
            map.put("msg", "小区信息保存成功!");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }*/
    public String saveBuilding() {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("building", jsonArray);

                RetVO retVo = buildingServiceManager.save(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存信息失败");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "保存小区信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String removeBuilding() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONArray json = JSONArray.fromObject(inParma);
            if (json.size() > 0) {
                inMap.put("community", json);
                RetVO retVo = buildingServiceManager.remove(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "删除成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "删除失败");
                }
            } else {
                map.put("result", "false");
                map.put("msg", "删除的信息不能为空");
            }

        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", "删除失败");
            e.printStackTrace();
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public int getPage() {
        return this.page;
    }

    public String getInParma() {
        return this.inParma;
    }

    public String getSuc_info() {
        return this.suc_info;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setInParma(String inParma) {
        this.inParma = inParma;
    }

    public void setSuc_info(String suc_info) {
        this.suc_info = suc_info;
    }
}
