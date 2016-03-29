package com.wuye.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.Room;
import com.wuye.entity.User;
import com.wuye.services.RoomServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


public class RoomAction extends ActionSupport {
    private Logger log = Logger.getLogger(RoomAction.class);
    @Autowired
    private RoomServiceManager roomServiceManager;
    //	@Autowired
//	private Manager baseManager;
    private String inParma = "";
    private String returnInfo = "";
    private int page;
    private String suc_info;

    public String saveRoom() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray != null && jsonArray.size() > 0) {
                inMap.put("room", jsonArray);

                RetVO retVo = roomServiceManager.save(inMap);
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

    public String getRoomList() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean isLogin = false;

            if (!StrUtil.isNullOrEmpty(inParma)) {
                JSONObject json = JSONObject.fromObject(inParma);
                //有传isQueryOne代表查询单独一个
                if (!StrUtil.isNullOrEmpty(json.get("isQueryOne"))
                        && "yes".equals(json.get("isQueryOne").toString())
                        && !StrUtil.isNullOrEmpty(json.get("roomId"))) {
                    map.put("roomId", json.get("roomId"));
                }
                //界面展示模糊查询的时候使用
                if (!StrUtil.isNullOrEmpty(json.get("roomName"))) {
                    map.put("roomName", json.get("roomName"));
                }
                if (StrUtil.isNullOrEmpty(page) || page < 1) {
                    page = 1;
                }
            }
            PageInfo pageInfo = roomServiceManager.getRoomList(
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
        return "list_Room";

    }

    public String getRoom() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);

            if (!StrUtil.isNullOrEmpty(json.get("communityId"))) {
                paramMap.put("communityId", NumericUtil.nullToIntegerZero(json.get("communityId")));
            }
            String type = StrUtil.strnull(json.get("qryType"));
            RetVO retvo = null;
            retvo = roomServiceManager.getRoom(paramMap, NumericUtil.nullToZero(page), 12);

			/*if (type.equals("getSimpleBuildingBycommunity")){
				retvo = roomServiceManager.getSimpleBuildingBycommunity(paramMap);
			}else{
				retvo = roomServiceManager.getBuilding(paramMap,NumericUtil.nullToZero(page), 12);
			}*/

            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getObject() != null) {
                    Map<String, Object> out = (Map<String, Object>) retvo.getObject();
                    JSONArray jsArray = (JSONArray) out.get("jsArray");
                    int pgcount = (Integer) out.get("pageCount");
                    retMap.put("data", jsArray);
                    retMap.put("pageCount", pgcount);
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

    /**
     * 删除物业公司
     */
    public String removePropertyCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!StrUtil.isNullOrEmpty(inParma)) {
                map.put("companyIdArray", JSONArray.fromObject(inParma));
                roomServiceManager.removeRoom(map);
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

    /**
     * 获取登录物业用户默认的物业公司
     */
    public String getDefaultPropertyCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = (User) ActionContext.getContext().getSession().get("user");
        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        returnInfo = jsontip.toString();
        return "default_user";
    }

    public String addPartyInfos() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            JSONObject roomJson = json.getJSONObject("room");
            JSONArray partryInfoArray = json.getJSONArray("partyInfos");
            Room room = new Room();
            EntityCopyUtil.populate(room, roomJson, new String[]{"roomId"});
            RetVO retVo = roomServiceManager.addPartyInfos(room, partryInfoArray);
            map.put("result", BaseConstants.RET_TRUE.equals(retVo.getResult()) ? "true" : "false");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;

    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getInParma() {
        return this.inParma;
    }

    public String getSuc_info() {
        return this.suc_info;
    }

    public void setInParma(String inParma) {
        this.inParma = inParma;
    }

    public void setSuc_info(String suc_info) {
        this.suc_info = suc_info;
    }
}
