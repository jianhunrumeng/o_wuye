package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;

import java.util.Map;

public interface BuildingServiceManager extends BaseManager {

    RetVO getBuilding(Map<String, Object> map, final int currentPage, final int perPageNum);

    RetVO save(Map<String, Object> inMap);

    RetVO remove(Map<String, Object> inMap);

    PageInfo getBuildingByName(String inParma, boolean b, Integer valueOf, int i);

    int getBuildingCount(Map<String, Object> map);

    RetVO getSimpleBuildingBycommunity(Map<String, Object> paramMap);

}
