package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.PartyInfo;
import com.wuye.entity.PropertyCompany;

import java.util.List;
import java.util.Map;


public interface PropertyCompanyServiceManager extends BaseManager {
    void savePreopertyCompany(Map<String, Object> map) throws Exception;

    public PageInfo getPreopertyCompanyList(Map<String, Object> map, final int currentPage, final int perPageNum);

    public PartyInfo getPartyInfo(Integer companyId);

    public void removePropertyCompany(Map<String, Object> map) throws Exception;

    /**
     * 根据物业公司名称查询
     *
     * @param name
     * @return
     */
    List<PropertyCompany> queryPropertyCompanyByName(String name);

    RetVO getCommunitySimple(Map<String, Object> paramMap);

}
