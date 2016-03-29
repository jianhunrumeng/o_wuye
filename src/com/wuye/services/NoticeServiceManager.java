package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.RetVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface NoticeServiceManager extends BaseManager {

    public void saveNotice(JSONObject notice, JSONArray entts, String classCode, String statusCd, String communityId);

    public RetVO queryNotice(String communityId, String noticeType, Integer pageNum, Integer pageSize);
}
