package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;
import com.wuye.entity.Community;

import java.util.List;
import java.util.Map;

public interface CommunityDao extends Dao {
    public void save(Community community);

    /**
     * 如果fuzzy为true，使用模糊查询
     *
     * @param communityName
     * @param fuzzy
     * @param currentPage
     * @param perPageNum
     * @return
     */
    public PageInfo getCommunityByName(String communityName, boolean fuzzy, final int currentPage, final int perPageNum);

    /**
     * 如果fuzzy为true，使用模糊查询
     *
     * @param simpleName
     * @param fuzzy
     * @param currentPage
     * @param perPageNum
     * @return
     */
    PageInfo getCommunityBySimpleName(String simpleName, boolean fuzzy, final int currentPage, final int perPageNum);

    public PageInfo getCommunity(Map<String, Object> map, final int currentPage, final int perPageNum);

    /**
     * 根据物业公司ID和小区名称查询
     *
     * @param community
     * @return
     */
    List<Community> queryCommunityByObj(String communityName, int companyId);
}
