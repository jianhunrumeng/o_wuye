package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.NoticeDao;
import com.wuye.entity.Notice;
import com.wuye.entity.NoticeRel;
import com.wuye.services.NoticeServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeServiceManager")
public class NoticeServiceManagerImpl extends BaseManagerImpl implements NoticeServiceManager {
    @Autowired
    NoticeDao noticeDao;

    public void saveNotice(JSONObject notice, JSONArray entts, String classCode, String statusCd, String communityId) {
        Notice noticeEntt = new Notice();
        EntityCopyUtil.populate(noticeEntt,
                notice, null);
        noticeEntt.save();
        String classId = this.getClassId(classCode);
        if (entts != null && entts.size() > 0) {
            for (int i = 0; i < entts.size(); i++) {
                NoticeRel rel = new NoticeRel();
                if ("Community".equals(classCode)) {
                    rel.setCommunityId(Integer.parseInt(entts.get(i) + ""));
                } else {
                    rel.setCommunityId(Integer.parseInt(communityId + ""));
                }
                rel.setClassId(classId);
                rel.setObjId(Integer.parseInt(entts.get(i) + ""));
                rel.setNotice(noticeEntt);
                rel.save();
            }
        }
    }

    private String getClassId(String classCode) {
        if ("Community".equals(classCode)) {
            return BaseConstants.CLASS_COMMUNITY;
        }
        if ("Building".equals(classCode)) {
            return BaseConstants.CLASS_BUILDING;
        }
        if ("Room".equals(classCode)) {
            return BaseConstants.CLASS_ROOM;
        }
        return "0";
    }

    public RetVO queryNotice(String communityId, String noticeType, Integer pageNum, Integer pageSize) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        retVO.setPageInfo(noticeDao.queryNotice(communityId, noticeType, pageNum, pageSize));
        return retVO;
    }

}
