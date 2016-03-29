package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.services.AcctItemTypeServiceManager;
import com.wuye.services.AttrSpecServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


public class AcctItemTypeAction extends ActionSupport {
    private Logger log = Logger.getLogger(AcctItemTypeAction.class);
    @Autowired
    private AcctItemTypeServiceManager acctItemTypeServiceManager;
    @Autowired
    private AttrSpecServiceManager attrSpecServiceManager;
    private int page;
    private String inParma;
    private String suc_info;

    public String getAcctItemType() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            String type = StrUtil.strnull(json.get("qryType"));
            String parentAcctItemTypeId = StrUtil.strnull(json.get("parentAcctItemTypeId"));
            RetVO retvo = null;
            paramMap.put("qryType", type);
            paramMap.put("parentAcctItemTypeId", parentAcctItemTypeId);
            retvo = acctItemTypeServiceManager.getAcctItemType(paramMap);

            if (retvo != null && BaseConstants.RET_TRUE.equals(retvo.getResult())) {
                if (retvo.getPageInfo() != null) {
                    retMap.put("data", retvo.getPageInfo().getDataList());
                }
                retMap.put("result", "true");
                retMap.put("msg", "费用类型信息查询成功!");

            } else {
                retMap.put("result", "false");
                retMap.put("msg", "费用类型信息查询失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("result", "false");
            retMap.put("msg", "费用类型信息查询失败!");
        }
        JSONObject jsonArr = JSONObject.fromObject(retMap);
        suc_info = jsonArr.toString();
        return SUCCESS;
    }

    /**
     * 获取公摊算法
     *
     * @return
     */
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
                retMap.put("msg", "计算方法信息查询失败!");

            } else {
                retMap.put("result", "false");
                retMap.put("msg", "计算方法信息查询失败!");
            }

            //JsonConfig jsonConfig = new JsonConfig();


        } catch (Exception e) {
            retMap.put("result", "false");
            retMap.put("msg", "计算方法信息查询失败!");
        }
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
