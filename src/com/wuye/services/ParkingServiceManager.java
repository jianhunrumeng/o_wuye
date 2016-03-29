package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.RetVO;

import java.util.Map;

public interface ParkingServiceManager extends BaseManager {
    public RetVO saveParkingType(Map<String, Object> map) throws Exception;

    public RetVO removeParkingType(Map<String, Object> map);

    public RetVO saveParking(Map<String, Object> map) throws Exception;

    public RetVO removeParking(Map<String, Object> map);

    public RetVO getParking(Map<String, Object> map,
                            final int currentPage, final int perPageNum);

    public RetVO getParkingType(Map<String, Object> map,
                                final int currentPage, final int perPageNum);
}
