package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;

import java.util.Map;

public interface ParkingDao extends Dao {

    PageInfo getParkingType(Map<String, Object> map, int currentPage,
                            int perPageNum);

    PageInfo getParking(Map<String, Object> map, int currentPage,
                        int perPageNum);

}
