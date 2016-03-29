package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.CommunityDao;
import com.wuye.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "communityDao")
public class CommunityDaoImpl extends BaseDaoHibernate implements CommunityDao {
    public PageInfo getCommunityByName(String communityName, boolean fuzzy, final int currentPage, final int perPageNum) {
        String hql = null;
        List<String> params = new ArrayList<String>();
        if (!StrUtil.isNullOrEmpty(communityName)) {
            if (fuzzy) {
                hql = "from Community c where c.communityName like ? ";
                params.add("%" + communityName + "%");
            } else {
                hql = "from Community c where c.communityName = ? ";
                params.add(communityName);
            }
        } else {
            hql = "from Community c ";
        }


        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql, params, currentPage, perPageNum);
        return pageInfo;
    }

    public PageInfo getCommunityBySimpleName(String simpleName, boolean fuzzy, final int currentPage, final int perPageNum) {
        // TODO Auto-generated method stub
        String hql = null;
        List<String> params = new ArrayList<String>();
        if (!StrUtil.isNullOrEmpty(simpleName)) {
            if (fuzzy) {
                hql = "from Community c where c.simpleName like ? ";
                params.add("%" + simpleName + "%");
            } else {
                hql = "from Community c where c.simpleName = ? ";
                params.add(simpleName);
            }
        } else {
            hql = "from Community c ";
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql, params, currentPage, perPageNum);
        return pageInfo;
    }

    public void save(Community community) {
        // TODO Auto-generated method stub
        community.save();
    }

    public PageInfo getCommunity(Map<String, Object> map, int currentPage,
                                 int perPageNum) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from Community c where 1=1 ");
        String fuzzy = (String) map.get("fuzzy");
        String communityName = (String) map.get("communityName");
        Integer communityId = (Integer) map.get("communityId");
        List<Object> params = new ArrayList<Object>();
        if (!StrUtil.isNullOrEmpty(communityName)) {
            if ("true".equals(fuzzy)) {
                hql.append(" and c.communityName like ? ");
                params.add("%" + communityName + "%");
            } else {
                hql.append(" and c.communityName = ? ");
                params.add(communityName);
            }
        }

        if (communityId != null && !communityId.equals(0)) {
            hql.append(" and c.communityId = ? ");
            params.add(communityId);
        }

        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        return pageInfo;
    }

    public List<Community> queryCommunityByObj(String communityName, int companyId) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from community where 1=1 ");
        sb.append(" and company_id = ? ");
        params.add(companyId);
        if (!StrUtil.isNullOrEmpty(communityName)) {
            sb.append(" and community_name like ? ");
            params.add("%" + communityName + "%");
        }
        sb.append(" limit 5 ");
        List<Community> community = this.jdbcFindList(sb.toString(), Community.class, params);
        return community;
    }
}
