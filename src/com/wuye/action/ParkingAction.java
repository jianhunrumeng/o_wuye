package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.services.AttrSpecServiceManager;
import com.wuye.services.ParkingServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ParkingAction extends ActionSupport {
    private Logger log = Logger.getLogger(CommunityAction.class);
    @Autowired
    private ParkingServiceManager parkingServiceManager;
    @Autowired
    private AttrSpecServiceManager attrSpecServiceManager;

    private int page;
    private String inParma;
    private String suc_info;

    public String getParking() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json.get("parkingId"))) {
                paramMap.put("parkingId", NumericUtil.nullToIntegerZero(json.get("parkingId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("parkingNbr"))) {
                paramMap.put("parkingNbr", StrUtil.strnull(json.get("parkingNbr")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo;

            retvo = parkingServiceManager.getParking(paramMap, NumericUtil.nullToZero(page), BaseConstants.DEFAULT_PAGE_PER_COUNT);

            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    Map<String, Object> out = (Map<String, Object>) retvo.getObject();
                    JSONArray jsArray = (JSONArray) out.get("jsArray");
                    int pgcount = (Integer) out.get("pageCount");
                    retMap.put("data", jsArray);
                    retMap.put("pageCount", pgcount);
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

    public String saveParking() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("parking", jsonArray);

                RetVO retVo = parkingServiceManager.saveParking(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存信息失败");
                }
            }

        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", "保存信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String removeParking() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONArray json = JSONArray.fromObject(inParma);
            if (json.size() > 0) {
                inMap.put("parking", json);
                RetVO retVo = parkingServiceManager.removeParking(inMap);
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

    public String getParkingType() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json.get("parkingTypeId"))) {
                paramMap.put("parkingTypeId", NumericUtil.nullToIntegerZero(json.get("parkingTypeId")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("parkingTypeName"))) {
                paramMap.put("parkingTypeName", StrUtil.strnull(json.get("parkingTypeName")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo;

            retvo = parkingServiceManager.getParkingType(paramMap, NumericUtil.nullToZero(page), BaseConstants.DEFAULT_PAGE_PER_COUNT);

            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    Map<String, Object> out = (Map<String, Object>) retvo.getObject();
                    JSONArray jsArray = (JSONArray) out.get("jsArray");
                    int pgcount = (Integer) out.get("pageCount");
                    retMap.put("data", jsArray);
                    retMap.put("pageCount", pgcount);
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

    public String saveParkingType() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("parkingType", jsonArray);

                RetVO retVo = parkingServiceManager.saveParkingType(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存信息失败");
                }
            }

        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", "保存信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String removeParkingType() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONArray json = JSONArray.fromObject(inParma);
            if (json.size() > 0) {
                inMap.put("parkingType", json);
                RetVO retVo = parkingServiceManager.removeParkingType(inMap);
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
