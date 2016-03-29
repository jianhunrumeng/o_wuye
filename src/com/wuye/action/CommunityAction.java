package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.date.JsonDateValueProcessor;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.Community;
import com.wuye.services.AttrSpecServiceManager;
import com.wuye.services.CommunityServiceManager;
import com.wuye.services.PropertyCompanyServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityAction extends ActionSupport {
    private Logger log = Logger.getLogger(CommunityAction.class);
    @Autowired
    private CommunityServiceManager communityServiceManager;
    @Autowired
    private AttrSpecServiceManager attrSpecServiceManager;
    @Autowired
    private PropertyCompanyServiceManager propertyCompanyServiceManager;

    private int page;
    private String inParma;
    private String suc_info;

    public String getCommunityCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageCount = communityServiceManager.getCommunityCount(map);
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

    public String getCommunityList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", 1);
        if (!StrUtil.isNullOrEmpty(inParma)) {
            map.put("communityName", inParma);
        }
        PageInfo pageInfo = communityServiceManager.getCommunityByName(inParma, true, Integer.valueOf(page), 12);
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

    public String getCommunity() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("communityName"))) {
                paramMap.put("communityName", StrUtil.strnull(json.get("communityName")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("companyId"))) {
                paramMap.put("companyId", StrUtil.strnull(json.get("companyId")));
            }
            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo;
            if (type.equals("getCommunitySimple")) {
                retvo = communityServiceManager.getCommunitySimple(paramMap);
            } else if ("building_room_simple".equals(type)) {
                retvo = communityServiceManager.getBuildingRoomsSimple(paramMap);
            } else {
                retvo = communityServiceManager.getCommunity(paramMap, NumericUtil.nullToZero(page), 12);
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

    public String addCommunity() {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (json != null) {
                Map<String, Object> inMap = new HashMap<String, Object>();
                inMap.put("community", json);
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
    }

    public String saveCommunity() {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("community", jsonArray);

                RetVO retVo = communityServiceManager.save(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存小区信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存小区信息失败");
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

    public String removeCommunity() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONArray json = JSONArray.fromObject(inParma);
            if (json.size() > 0) {
                inMap.put("community", json);
                RetVO retVo = communityServiceManager.remove(inMap);
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

    public String getRelSetting() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONArray json = JSONArray.fromObject(inParma);
            if (json.size() > 0) {
                paramMap.put("attrCd", json);
                RetVO retVo = attrSpecServiceManager.getAttrSpec(paramMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    if (retVo.getObject() != null) {
                        JSONArray jsArray = (JSONArray) retVo.getObject();
                        suc_info = jsArray.toString();
                        retMap.put("data", jsArray);
                    }

                    retMap.put("result", "true");
                    retMap.put("msg", "查询成功");
                } else {
                    retMap.put("result", "false");
                    retMap.put("msg", "查询失败");
                }
            } else {
                retMap.put("result", "true");
                retMap.put("msg", "查询成功");
            }
        } catch (Exception e) {
            retMap.put("result", "false");
            retMap.put("msg", "查询失败!");
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

    public String getAttrValue() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (json.containsKey("qryType")) {
                String qryType = json.getString("qryType");
                paramMap.put("qryType", qryType);
            }


            if (!StrUtil.isNullOrEmpty(json.get("attrId"))) {
                paramMap.put("attrId", NumericUtil.nullToIntegerZero(json.get("attrId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("attrCd"))) {
                paramMap.put("attrCd", StrUtil.strnull(json.get("attrCd")));
            }

            RetVO retvo = attrSpecServiceManager.getAttrValue(paramMap);
            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    JSONArray jsArray = (JSONArray) retvo.getObject();
                    suc_info = jsArray.toString();
                    retMap.put("data", jsArray);
                }
                retMap.put("result", "true");
                retMap.put("msg", "信息查询成功!");

            } else {
                retMap.put("result", "false");
                retMap.put("msg", "信息查询失败!");
            }

            //JsonConfig jsonConfig = new JsonConfig();


        } catch (Exception e) {
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

    /**
     * 保存属性
     *
     * @return
     */
    public String saveAttr() {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("attr", jsonArray);

                RetVO retVo = attrSpecServiceManager.save(inMap);
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
            map.put("msg", "保存信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String queryFiveCommunityList() {
        if (!StrUtil.isNullOrEmpty(inParma) && !StrUtil.isNullOrEmpty(JSONObject.fromObject(inParma).get("companyId"))) {
            JSONObject obj = JSONObject.fromObject(inParma);
            int companyId = obj.getInt("companyId");
            String communityName = obj.getString("communityName");
            JSONArray json = new JSONArray();
            List<Community> communityList = communityServiceManager.queryCommunityByNameAndCompanyId(communityName, companyId);
            for (Community com : communityList) {
                Map<String, Object> m = new HashMap<String, Object>();
                String comName = com.getCommunityName();
                int comId = com.getCommunityId();
                m.put("communityId", comId);
                m.put("communityName", comName);
                json.add(m);
            }
            suc_info = json.toString();
        }
        return SUCCESS;
    }

    public String getCompany() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json.get("companyId"))) {
                paramMap.put("companyId", NumericUtil.nullToIntegerZero(json.get("companyId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("companyName"))) {
                paramMap.put("companyName", StrUtil.strnull(json.get("companyName")));
            }

            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo = null;
            if (type.equals("getCompanySimple")) {
                retvo = propertyCompanyServiceManager.getCommunitySimple(paramMap);
            }
            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    JSONArray jsArray = (JSONArray) retvo.getObject();
                    suc_info = jsArray.toString();
                    retMap.put("data", jsArray);
                }
                retMap.put("result", "true");
                retMap.put("msg", "信息查询成功!");

            } else {
                retMap.put("result", "false");
                retMap.put("msg", "信息查询失败!");
            }

            //JsonConfig jsonConfig = new JsonConfig();


        } catch (Exception e) {
            retMap.put("result", "false");
            retMap.put("msg", "信息查询失败!");
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
