package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;
import com.wuye.entity.Room;
import com.wuye.entity.RoomPartyRel;

import java.util.Map;

public interface RoomDao extends Dao {

    PageInfo getRoom(Map<String, Object> map, int currentPage, int perPageNum);

    public RoomPartyRel getOwnerRel(Room room);
}
