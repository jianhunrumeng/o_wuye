package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AcctItemRelDao;
import com.wuye.entity.AcctItemRel;
import com.wuye.services.AcctItemRelServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("acctItemRelServiceManager")
public class AcctItemRelServiceManagerImpl extends BaseManagerImpl implements
        AcctItemRelServiceManager {
    @Autowired
    AcctItemRelDao acctItemRelDao;

    public RetVO getAcctItemType(Map<String, Object> map) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = acctItemRelDao.getAcctItemType(map);
//		List<Object> dataList = pg.getDataList();
        retVO.setPageInfo(pg);
        return retVO;
    }

    public RetVO save(JSONObject json) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONObject propertyCompany = null;
        JSONArray acctItemRels = null;
        JSONObject community = null;
        JSONObject building = null;
        JSONObject floor = null;
        String isUpdate = "";
        String saveType = null;
        if (json.containsKey("propertyCompany")) {
            propertyCompany = json.getJSONObject("propertyCompany");
        }
        if (json.containsKey("acctItemRels")) {
            acctItemRels = json.getJSONArray("acctItemRels");
        }
        if (json.containsKey("community")) {
            community = json.getJSONObject("community");
        }
        if (json.containsKey("building")) {
            JSONObject buildingObj = json.getJSONObject("building");
            if (!buildingObj.isNullObject()) {
                building = buildingObj.getJSONObject("building");
            }
        }
        if (json.containsKey("floor")) {
            floor = json.getJSONObject("floor");
        }
        if (json.containsKey("isUpdate")) {
            isUpdate = json.getString("isUpdate");
        }
        if (json.containsKey("saveType")) {
            saveType = json.getString("saveType");
        }
        if (acctItemRels != null && acctItemRels.size() > 0) {
            for (int i = 0; i < acctItemRels.size(); i++) {
                JSONObject srcAcctItemRel = acctItemRels.getJSONObject(i);
                if (BaseConstants.RET_TRUE.equals(isUpdate)) {
                    AcctItemRel acctItemRel = (AcctItemRel) this.dao.getObject(AcctItemRel.class, srcAcctItemRel.getInt("acctItemRelId"));
                    EntityCopyUtil.populate(acctItemRel, srcAcctItemRel, new String[]{"price", "caculateMethod"});
                    acctItemRel.save();
                } else {
                    if (StrUtil.isNullOrEmpty(saveType)) {
                        retVo.setRetCode(BaseConstants.RET_FALSE);
                        retVo.setRetMsg("保存类型必填！");
                        return retVo;
                    }
                    AcctItemRel acctItemRel = new AcctItemRel();
                    EntityCopyUtil.populate(acctItemRel, srcAcctItemRel, new String[]{"price"});
                    //计算方法
                    if (!StrUtil.isNullOrEmpty(StrUtil.strnull(srcAcctItemRel.get("attrValue")))) {
                        acctItemRel.setCaculateMethod(StrUtil.strnull(srcAcctItemRel.get("attrValue")));
                    }
                    if (BaseConstants.CLASS_COMMUNITY.equals(saveType)) {
                        if (StrUtil.isNullOrEmpty(community)) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("小区信息传入错误！");
                            return retVo;
                        }
                        acctItemRel.setClassId(BaseConstants.CLASS_COMMUNITY);
                        acctItemRel.setObjId(Integer.parseInt(community.get("communityId") + ""));
                    } else if (BaseConstants.CLASS_BUILDING.equals(saveType)) {
                        if (StrUtil.isNullOrEmpty(building)) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("小区信息传入错误！");
                            return retVo;
                        }
                        acctItemRel.setClassId(BaseConstants.CLASS_BUILDING);
                        acctItemRel.setObjId(Integer.parseInt(building.get("buildingId") + ""));
                    } else if (BaseConstants.CLASS_FLOOR.equals(saveType)) {
                        if (StrUtil.isNullOrEmpty(floor) || StrUtil.isNullOrEmpty(floor.getInt("floorId"))) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("楼层信息传入错误！");
                            return retVo;
                        }
                        acctItemRel.setClassId(BaseConstants.CLASS_FLOOR);
                        acctItemRel.setFloor(floor.getInt("floorId"));
                    }
                    acctItemRel.setAcctItemTypeId(Integer.valueOf(srcAcctItemRel.get("childAcctItemTypeId") + ""));
                    acctItemRel.save();
                }
            }
        }
        return retVo;
    }

    public RetVO remove(JSONObject json) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONArray acctItemRels = json.getJSONArray("acctItemRels");
        if (acctItemRels != null && acctItemRels.size() > 0) {
            for (int i = 0; i < acctItemRels.size(); i++) {
                JSONObject obj = acctItemRels.getJSONObject(i);
                if (obj != null && !StrUtil.isNullOrEmpty(obj.getInt("acctItemRelId"))) {
                    AcctItemRel acctItemRel = acctItemRel = (AcctItemRel) this.dao.getObject(AcctItemRel.class, obj.getInt("acctItemRelId"));
                    if (!StrUtil.isNullOrEmpty(acctItemRel)) {
                        //若是全选则直接删除
                        if (json.containsKey("selectAll")
                                && json.getBoolean("selectAll")
                                && obj.containsKey("selected")
                                && obj.getBoolean("selected")) {
                            acctItemRel.remove();
                        } else if (obj.containsKey("selected")
                                && obj.getBoolean("selected")) {//否则判断下面的单个是否选中，选中则删除
                            acctItemRel.remove();
                        }
                    }
                }
            }
        }
        return retVo;
    }

    /**
     * add by tanyw
     * 20150109
     * 查询费用配置
     */
    public RetVO queryAcctItemRels(JSONObject json) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        Map map = new HashMap();
        for (Iterator iter = json.keys(); iter.hasNext(); ) {
            String key = (String) iter.next();
            map.put(key, json.get(key));
        }
        PageInfo pg = acctItemRelDao.queryAcctItemRels(map);
        List<Object> dataList = pg.getDataList();
        retVO.setDataList(dataList);
        return retVO;
    }

}
