package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.services.AcctItemRelServiceManager;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class AcctItemRelAction extends ActionSupport {
    private Logger log = Logger.getLogger(AcctItemRelAction.class);
    @Autowired
    private AcctItemRelServiceManager acctItemRelServiceManager;

    private int page;
    private String inParma;
    private String suc_info;

    public String saveAcctItemRel() {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (!StrUtil.isNullOrEmpty(json)) {
                RetVO retVo = acctItemRelServiceManager.save(json);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("msg", "保存信息成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "保存信息失败:" + retVo.getRetMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "false");
            map.put("msg", "保存费用信息失败");
            log.info(e.getMessage());
        }
        JSONObject jsontip = JSONObject.fromObject(map);
        suc_info = jsontip.toString();
        return SUCCESS;
    }

    public String removeAcctItemRel() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (json.size() > 0) {
                RetVO retVo = acctItemRelServiceManager.remove(json);
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

    /**
     * 获取配置的费用信息
     *
     * @return
     */
    public String queryAcctItemRels() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        try {
            JSONObject json = JSONObject.fromObject(inParma);
            if (json.size() > 0) {
                RetVO retVo = acctItemRelServiceManager.queryAcctItemRels(json);
                if (BaseConstants.RET_TRUE.equals(retVo.getResult())) {
                    map.put("result", "true");
                    map.put("data", retVo.getDataList());
                    map.put("msg", "查询成功");
                } else {
                    map.put("result", "false");
                    map.put("msg", "查询失败");
                }
            }

        } catch (Exception e) {
            map.put("result", "false");
            map.put("msg", "查询失败");
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
