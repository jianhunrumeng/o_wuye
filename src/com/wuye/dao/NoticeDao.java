package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;

public interface NoticeDao extends Dao {

    public PageInfo queryNotice(String communityId, String noticeType, Integer pageNum,
                                Integer pageSize);
}
