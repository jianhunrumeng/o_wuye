package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.RetVO;
import net.sf.json.JSONObject;

import java.util.Map;

public interface MeterSpecServiceManager extends BaseManager {

    RetVO getAcctItemType(Map<String, Object> map);

    RetVO save(JSONObject json);

    RetVO remove(JSONObject json);

    RetVO queryMeterSpecs(JSONObject json);

}
