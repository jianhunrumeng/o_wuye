package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.MeterSpecDao;
import com.wuye.entity.AcctItemRel;
import com.wuye.entity.MeterSpec;
import com.wuye.services.MeterSpecServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("meterSepcServiceManager")
public class MeterSpecServiceManagerImpl extends BaseManagerImpl implements
        MeterSpecServiceManager {
    @Autowired
    MeterSpecDao meterSpecDao;

    public RetVO getAcctItemType(Map<String, Object> map) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = meterSpecDao.getAcctItemType(map);
//		List<Object> dataList = pg.getDataList();
        retVO.setPageInfo(pg);
        return retVO;
    }

    public RetVO save(JSONObject json) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONObject propertyCompany = null;
        JSONArray meterSpecs = null;
        JSONObject community = null;
        JSONObject building = null;
        JSONObject floor = null;
        String isUpdate = "";
        String saveType = null;
        if (json.containsKey("propertyCompany")) {
            propertyCompany = json.getJSONObject("propertyCompany");
        }
        if (json.containsKey("meterSpecs")) {
            meterSpecs = json.getJSONArray("meterSpecs");
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
        //费用规格上是放在小区上面的
        if (!BaseConstants.CLASS_COMMUNITY.equals(saveType)) {
            retVo.setRetCode(BaseConstants.RET_FALSE);
            retVo.setRetMsg("只能给小区添加费用规格！");
            return retVo;
        }
        if (meterSpecs != null && meterSpecs.size() > 0) {
            for (int i = 0; i < meterSpecs.size(); i++) {
                JSONObject srcMeterSpecs = meterSpecs.getJSONObject(i);
                if (BaseConstants.RET_TRUE.equals(isUpdate)) {
                    MeterSpec meterSpec = (MeterSpec) this.dao.getObject(AcctItemRel.class, srcMeterSpecs.getInt("acctItemRelId"));
                    EntityCopyUtil.populate(meterSpec, srcMeterSpecs, new String[]{"meterName", "price"});
                    meterSpec.save();
                } else {
                    if (StrUtil.isNullOrEmpty(saveType)) {
                        retVo.setRetCode(BaseConstants.RET_FALSE);
                        retVo.setRetMsg("未选择主体对象！");
                        return retVo;
                    }
                    MeterSpec meterSpec = new MeterSpec();
                    EntityCopyUtil.populate(meterSpec, srcMeterSpecs, new String[]{"parentMeterId", "meterName", "price", "meterType"});
                    JSONObject parentObj = srcMeterSpecs.getJSONObject("parentMeterSpec");
                    if (parentObj.isNullObject()) {
                        retVo.setRetCode(BaseConstants.RET_FALSE);
                        retVo.setRetMsg("费表规格大类传入错误！");
                        return retVo;
                    }
                    meterSpec.setParentMeterId(parentObj.getInt("meterSpecId"));
                    meterSpec.setMeterType(parentObj.getString("meterType"));
                    meterSpec.setUnit(parentObj.getString("unit"));
                    if (BaseConstants.CLASS_COMMUNITY.equals(saveType)) {
                        if (community.isNullObject()) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("小区信息传入错误！");
                            return retVo;
                        }
                        meterSpec.setClassId(BaseConstants.CLASS_COMMUNITY);
                        meterSpec.setObjId(Integer.parseInt(community.get("communityId") + ""));
                    } else if (BaseConstants.CLASS_BUILDING.equals(saveType)) {
                        if (building.isNullObject()) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("楼栋信息传入错误！");
                            return retVo;
                        }
                        meterSpec.setClassId(BaseConstants.CLASS_BUILDING);
                        meterSpec.setObjId(Integer.parseInt(building.get("buildingId") + ""));
                    } else if (BaseConstants.CLASS_FLOOR.equals(saveType)) {
                        if (StrUtil.isNullOrEmpty(floor) || StrUtil.isNullOrEmpty(floor.getInt("floorId"))) {
                            retVo.setRetCode(BaseConstants.RET_FALSE);
                            retVo.setRetMsg("楼层信息传入错误！");
                            return retVo;
                        }
                        meterSpec.setClassId(BaseConstants.CLASS_FLOOR);
//						meterSpec.setFloor(floor.getInt("floorId"));
                    }
                    meterSpec.save();
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
     * 20150123
     * 查询费表配置
     */
    public RetVO queryMeterSpecs(JSONObject json) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        Map map = new HashMap();
        for (Iterator iter = json.keys(); iter.hasNext(); ) {
            String key = (String) iter.next();
            map.put(key, json.get(key));
        }
        PageInfo pg = meterSpecDao.queryMeterSpecs(map);
        List<Object> dataList = pg.getDataList();
        retVO.setDataList(dataList);
        return retVO;
    }

}
