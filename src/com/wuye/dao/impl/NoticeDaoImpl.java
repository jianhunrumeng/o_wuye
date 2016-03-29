package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.NoticeDao;
import com.wuye.services.vo.NoticeVo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoHibernate implements NoticeDao {

    public PageInfo queryNotice(String communityId, String noticeType, Integer pageNum,
                                Integer pageSize) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append("select * from notice a, notice_rel b where a.notice_id = b.notice_id");
        sb.append(" and a.notice_type = ? ");
        sb.append("and b.community_id = ?");
        params.add(noticeType);
        params.add(communityId);
        return this.jdbcFindPageInfo(sb.toString(), NoticeVo.class, params, pageNum, pageSize);
    }
}
