package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.PartyInfo;
import com.wuye.entity.Room;
import net.sf.json.JSONArray;

import java.util.Map;


public interface RoomServiceManager extends BaseManager {
    public PageInfo getRoomList(Map<String, Object> map, final int currentPage, final int perPageNum);

    public PartyInfo getPartyInfo(Integer companyId);

    public void removeRoom(Map<String, Object> map) throws Exception;

    RetVO getRoom(Map<String, Object> map, final int currentPage, final int perPageNum);

    RetVO save(Map<String, Object> inMap);

    RetVO addPartyInfos(Room room, JSONArray partryInfoArray);
}
