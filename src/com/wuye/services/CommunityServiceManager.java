package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.Community;
import com.wuye.entity.PartyInfo;

import java.util.List;
import java.util.Map;

public interface CommunityServiceManager extends BaseManager {
    public void save(Community community);

    public void save(List<Community> communitys);

    public int getCommunityCount(Map<String, Object> map);

    public PageInfo getCommunityByName(String communityName, boolean fuzzy, final int currentPage, final int perPageNum);

    public PageInfo getCommunityBySimpleName(String simpleName, boolean fuzzy, final int currentPage, final int perPageNum);

    public PartyInfo getPartyInfo(Integer communityId);

    public RetVO getCommunity(Map<String, Object> map, final int currentPage, final int perPageNum);

    public RetVO save(Map<String, Object> map) throws Exception;

    public RetVO remove(Map<String, Object> map);

    /**
     * 根据小区名称和物业公司ID
     *
     * @param communityName
     * @param companyId
     * @return
     */
    List<Community> queryCommunityByNameAndCompanyId(String communityName, int companyId);

    public RetVO getCommunitySimple(Map<String, Object> map);

    public RetVO getBuildingRoomsSimple(Map<String, Object> paramMap);
}
