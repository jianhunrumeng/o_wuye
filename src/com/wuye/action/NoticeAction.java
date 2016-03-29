package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.date.JsonDateValueProcessor;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.entity.AttrValue;
import com.wuye.services.AttrValueService;
import com.wuye.services.NoticeServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeAction extends ActionSupport {

    @Autowired
    private NoticeServiceManager noticeServiceManager;

    @Autowired
    private AttrValueService attrValueService;

    private String jsondata;

    private String suc_info = "";

    private Integer pageNum;

    private Integer pageSize;

    /**
     * 查询通知类型
     * .
     *
     * @return
     * @author FFCS-ZHENGGW
     * 2016-1-4 FFCS-ZHENGGW
     */
    public String queryNoticeType() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<AttrValue> attrValueList = attrValueService.getAttrValueList("Notice", "noticeType");
            if (attrValueList != null && attrValueList.size() > 0) {
                JSONArray jsArray = new JSONArray();
                for (AttrValue attrValue : attrValueList) {
                    JSONObject jsObj = EntityCopyUtil.getJSONObject(attrValue, null);
                    jsArray.add(jsObj);
                }
                map.put("data", jsArray);
            }
            map.put("result", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 保存通知
     * .
     *
     * @return
     * @author FFCS-ZHENGGW
     * 2016-1-4 FFCS-ZHENGGW
     */
    public String saveNotice() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            JSONObject json = JSONObject.fromObject(jsondata);
            JSONObject notice = json.getJSONObject("notice");
            JSONArray entts = json.getJSONArray("entts");
            String classCode = json.get("classCode") + "";
            String statusCd = StrUtil.isNullOrEmpty(json.get("statusCd")) ? BaseConstants.STATUS_VALID : json.get("statusCd") + "";
            String communityId = json.get("communityId") + "";
            noticeServiceManager.saveNotice(notice, entts, classCode, statusCd, communityId);
            map.put("result", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    /**
     * 查询通知
     * .
     *
     * @return
     * @author FFCS-ZHENGGW
     * 2016-1-4 FFCS-ZHENGGW
     */
    public String queryNotice() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(jsondata);
            String communityId = json.get("communityId") + "";
            String noticeType = json.get("noticeType") + "";
            Integer pageNum = StrUtil.isNullOrEmpty(this.pageNum) ? 1 : this.pageNum;
            Integer pageSize = StrUtil.isNullOrEmpty(this.pageSize) ? BaseConstants.DEFAULT_PAGE_PER_COUNT : this.pageSize;
            RetVO retVO = noticeServiceManager.queryNotice(communityId, noticeType, pageNum, pageSize);
            map.put("data", retVO.getPageInfo().getDataList());
            map.put("result", BaseConstants.RET_TRUE.equals(retVO.getResult()) ? "true" : "false");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject jsontip = JSONObject.fromObject(map, jsonConfig);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String getJsondata() {
        return this.jsondata;
    }

    public String getSuc_info() {
        return this.suc_info;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public void setSuc_info(String suc_info) {
        this.suc_info = suc_info;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
