package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AttrSpecDao;
import com.wuye.entity.AttrSpec;
import com.wuye.entity.AttrValue;
import com.wuye.services.AttrSpecServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("attrSpecServiceManager")
public class AttrSpecServiceManagerImpl extends BaseManagerImpl implements
        AttrSpecServiceManager {
    @Autowired
    AttrSpecDao attrSpecDao;

    public RetVO getAttrSpec(String attrCd) {
        RetVO ret = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        List<AttrSpec> attr = attrSpecDao.getAttr(attrCd);
        ret.setObject(attr);
        return ret;
    }

    public RetVO getAttrSpec(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        List<AttrSpec> ret = attrSpecDao.getAttr(map);
        JSONArray jsArr = new JSONArray();
        if (ret != null && ret.size() > 0) {
            for (AttrSpec attr : ret) {
                JSONObject jsobj = EntityCopyUtil.getJSONObject(attr, null);
                jsArr.add(jsobj);
            }
        }
        retVo.setObject(jsArr);
        return retVo;
    }

    public RetVO getAttrValue(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        List<AttrValue> ret = attrSpecDao.getAttrValue(map);
        JSONArray jsArr = new JSONArray();
        if (ret != null && ret.size() > 0) {
            for (AttrValue attrValue : ret) {
                JSONObject jsobj = EntityCopyUtil
                        .getJSONObject(attrValue, null);
                jsArr.add(jsobj);
            }
        }
        retVo.setObject(jsArr);
        return retVo;
    }

    public RetVO save(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");

        JSONArray attrValueObj = (JSONArray) map.get("attrValue");
        if (attrValueObj != null && attrValueObj.size() > 0) {
            for (int i2 = 0; i2 < attrValueObj.size(); i2++) {
                JSONObject jsobj = attrValueObj.getJSONObject(i2);
                String act = jsobj.getString("action");
                if (BaseConstants.ACTION_DEL.equals(act)) {
                    this.delAttrValue(jsobj);
                } else if (BaseConstants.ACTION_UPDATE.equals(act)) {
                    this.updateAttrValue(jsobj);
                } else if (BaseConstants.ACTION_ADD.equals(act)) {
                    this.addAttrValue(jsobj);
                }
            }
        }

        return retVo;
    }

    private void addAttrValue(JSONObject jsobj) {
        AttrValue attrValue = new AttrValue();
        AttrSpec attr = (AttrSpec) attrSpecDao.getObject(AttrSpec.class, NumericUtil.nullToIntegerZero(jsobj.get("attrId")));
        attrValue.setAttrSpec(attr);
        attrValue.setAttrValue(jsobj.getString("attrValue"));
        attrValue.setAttrValueName(jsobj.getString("attrValue"));
        if (!StrUtil.isNullOrEmpty(jsobj.getString("communityId"))) {
            attrValue.setCommunityId(jsobj.getInt("communityId"));
        }

        attrValue.save();
    }

    private void updateAttrValue(JSONObject jsobj) {
        AttrValue attrValue = (AttrValue) attrSpecDao.getObject(AttrValue.class, NumericUtil.nullToIntegerZero(jsobj.getString("attrValueId")));
        if (attrValue != null) {
            attrValue.setAttrValue(jsobj.getString("attrValue"));
            attrValue.setAttrValueName(jsobj.getString("attrValue"));
            attrValue.save();
        }
    }

    private void delAttrValue(JSONObject jsobj) {
        AttrValue attrValue = (AttrValue) attrSpecDao.getObject(AttrValue.class, NumericUtil.nullToIntegerZero(jsobj.getString("attrValueId")));
        if (attrValue != null) {
            attrValue.setStatusCd(BaseConstants.STATUS_INVALID);
            attrValue.save();
        }
    }
}
