package com.wuye.action;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sdicons.json.mapper.MapperException;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.json.JSONUtils;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.*;
import com.wuye.services.UserServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {
    private static Logger log = Logger.getLogger(UserAction.class);

    @Autowired
    private UserServiceManager userServiceManager;
    private String comingParams;
    private String suc_info = "";
    private int page;

    public String saveUser() {
        log.info("传入的参数:" + comingParams);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        JSONObject objJson = JSONObject.fromObject(comingParams);
        try {
            User user = JSONUtils.jsonToObject(objJson.get("user").toString(), User.class);
            Boolean bl = userServiceManager.isExitAccount(user.getAccount());
            if (bl) {
                returnMap.put("key", "isExit");
            } else {
                Map<String, Object> map = new HashMap<String, Object>();

                if (!StrUtil.isNullOrEmpty(objJson.getJSONObject("community").get("communityId"))) {
                    Community community = JSONUtils.jsonToObject(objJson.get("community").toString(), Community.class);
                    map.put("cmmunity", community);
                }
                if (!StrUtil.isNullOrEmpty(objJson.getJSONObject("propertyCompany").get("companyId"))) {
                    PropertyCompany pCompany = JSONUtils.jsonToObject(objJson.get("propertyCompany").toString(), PropertyCompany.class);
                    map.put("proertyCompany", pCompany);
                }
                if (!StrUtil.isNullOrEmpty(objJson.get("partyInfoUser"))) {
                    PartyInfo partyInfoUser = JSONUtils.jsonToObject(objJson.get("partyInfoUser").toString(), PartyInfo.class);
                    map.put("partyInfo", partyInfoUser);
                }
                if (!StrUtil.isNullOrEmpty(objJson.get("role"))) {
                    JSONArray roleArray = objJson.getJSONArray("role");
                    List<Role> roleList = new ArrayList<Role>();
                    for (int i = 0; i < roleArray.size(); i++) {
                        Role role = new Role();
                        role.setId(roleArray.getInt(i));
                        roleList.add(role);
                    }
                    map.put("roleList", roleList);
                }
                if (!StrUtil.isNullOrEmpty(objJson.get("address"))) {
                    Address address = JSONUtils.jsonToObject(objJson.get("address").toString(), Address.class);
                    map.put("address", address);
                }
                if (!StrUtil.isNullOrEmpty(objJson.get("area"))) {
                    Area area = JSONUtils.jsonToObject(objJson.get("area").toString(), Area.class);
                    map.put("area", area);
                }
                String defaultPW = "wy123123";
                user.setPwd(defaultPW);
                map.put("user", user);

                userServiceManager.saveUser(map);
                returnMap.put("key", "success");
                returnMap.put("msg", "用户信息保存成功!");
            }

        } catch (TokenStreamException e) {

            e.printStackTrace();
            returnMap.put("msg", e);
            returnMap.put("key", "false");
        } catch (RecognitionException e) {

            e.printStackTrace();
            returnMap.put("msg", e);
            returnMap.put("key", "false");
        } catch (MapperException e) {

            e.printStackTrace();
            returnMap.put("msg", e);
            returnMap.put("key", "false");
        }
        JSONObject json = JSONObject.fromObject(returnMap);
        suc_info = json.toString();
        return "save_success";
    }
    //获取用户所在小区信息和受管理的业务公司
    public String getOrganizationByUser() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        Map<String, Object> map = new HashMap<String, Object>();
        if (BaseConstants.USER_TYPE_11.equals(user.getUserType())) {  //物业用户
            map.put("propertyCompany", new PropertyCompany());
            map.put("community", new Community());
        } else {
            map.put("isSys", "yes");
        }
        JSONObject json = JSONObject.fromObject(map);
        suc_info = json.toString();
        return "organization_info";
    }

    public String getUser() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(comingParams);
            if (!StrUtil.isNullOrEmpty(json.get("account"))) {
                paramMap.put("account", NumericUtil.nullToIntegerZero(json.get("account")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("companyName"))) {
                paramMap.put("companyName", StrUtil.strnull(json.get("companyName")));
            }

            if (!StrUtil.isNullOrEmpty(json.get("partyName"))) {
                paramMap.put("partyName", StrUtil.strnull(json.get("partyName")));
            }
            /*if (!StrUtil.isNullOrEmpty(json.get("communityName"))){
                paramMap.put("communityName", StrUtil.strnull(json.get("communityName")));
			}*/
            if (!StrUtil.isNullOrEmpty(json.get("qryType"))) {
                paramMap.put("qryType", StrUtil.strnull(json.get("qryType")));
            }
            RetVO retvo = null;
			/*if(type.equals("getCompanySimple")){
				retvo = propertyCompanyServiceManager.getCommunitySimple(paramMap);
			}*/
            retvo = userServiceManager.getUser(paramMap, Integer.valueOf(page), BaseConstants.DEFAULT_PAGE_PER_COUNT);
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

    /**
     * 获取客户信息.
     *
     * @return
     * @author Luxb
     * 2015-12-19 Luxb
     */
    public String qryUserList() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(comingParams);
            if (!StrUtil.isNullOrEmpty(json.get("account"))) {
                paramMap.put("account", json.get("account"));
            }
            if (!StrUtil.isNullOrEmpty(json.get("companyName"))) {
                paramMap.put("companyName", StrUtil.strnull(json.get("companyName")));
            }

            if (!StrUtil.isNullOrEmpty(json.get("partyName"))) {
                paramMap.put("partyName", StrUtil.strnull(json.get("partyName")));
            }
            if (!StrUtil.isNullOrEmpty(json.get("qryType"))) {
                paramMap.put("qryType", StrUtil.strnull(json.get("qryType")));
            }

            PageInfo pageInfo = userServiceManager.qryUserList(paramMap, page, 3);
            JSONObject pg = new JSONObject();
            if (pageInfo != null) {
                pg.put("perPageCount", pageInfo.getPerPageCount());
                pg.put("totalPageCount", pageInfo.getTotalPageCount());
                pg.put("currentPage", pageInfo.getCurrentPage());
                pg.put("totalCount", pageInfo.getTotalCount());
                List<Object> dataList = pageInfo.getDataList();
                JSONArray jsArray = new JSONArray();
                if (dataList != null && dataList.size() > 0) {
                    for (Object obj : dataList) {
                        User user = (User) obj;
                        PartyInfo partyInfo = user.getPartyInfo();
                        JSONObject jsobj = EntityCopyUtil.getJSONObject(user, null);
                        if (partyInfo != null) {
                            jsobj.put("partyInfo", EntityCopyUtil.getJSONObject(partyInfo, null));
                            Address address = partyInfo.getAddress();
                            PropertyCompany company = user.getOwnerCompany();
                            jsobj.put("company", EntityCopyUtil.getJSONObject(company, null));
                            jsobj.put("address", EntityCopyUtil.getJSONObject(address, null));
                        }

                        jsArray.add(jsobj);
                        pg.put("dataList", jsArray);
                    }
                }
            }

            retMap.put("pageInfo", pg);
            retMap.put("result", "true");
            retMap.put("msg", "用户查询查询成功!");
        } catch (Exception e) {
            retMap.put("result", "false");
            retMap.put("msg", "信息查询失败!");
        }
        JSONObject jsonArr = JSONObject.fromObject(retMap);
        suc_info = jsonArr.toString();
        return SUCCESS;
    }

    /**
     * 用户删除.
     *
     * @return
     * @author Luxb
     * 2015-12-19 Luxb
     */
    public String removeUser() {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            if (comingParams != null) {
                inMap.put("userIdArray", JSONArray.fromObject(comingParams));

                RetVO retVo = userServiceManager.del(inMap);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "用户删除成功！");
                } else {
                    map.put("result", "false");
                    map.put("msg", "用户删除失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "用户删除失败！");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
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
