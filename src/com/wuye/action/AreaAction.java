package com.wuye.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.entity.Area;
import com.wuye.services.AreaServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaAction extends ActionSupport {
    private static Logger log = Logger.getLogger(AreaAction.class);
    @Autowired
    private AreaServiceManager areaServiceManager;

    private int upAreaId;
    private String areaListStr;
    private String pzJson;

    public String queryAreas() {
        List<Area> areaList = areaServiceManager.getAreas(upAreaId);
        JSONArray jsarr = new JSONArray();
        if (areaList != null && areaList.size() > 0) {
            for (Area area : areaList) {
                JSONObject jsobj = EntityCopyUtil.getJSONObject(area, null);
                jsarr.add(jsobj);
            }
        }

        log.info("return areaJson:" + jsarr);
        areaListStr = jsarr.toString();
        return "success";
    }

    public int getUpAreaId() {
        return upAreaId;
    }

    public void setUpAreaId(int upAreaId) {
        this.upAreaId = upAreaId;
    }

    public String getAreaListStr() {
        return areaListStr;
    }

    public void setAreaListStr(String areaListStr) {
        this.areaListStr = areaListStr;
    }

    public String getPzJson() {
        return pzJson;
    }

    public void setPzJson(String pzJson) {
        this.pzJson = pzJson;
    }

}
