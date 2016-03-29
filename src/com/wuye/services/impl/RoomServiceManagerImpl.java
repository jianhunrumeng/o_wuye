package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PartyInfoDao;
import com.wuye.dao.PropertyCompanyDao;
import com.wuye.dao.RoomDao;
import com.wuye.entity.*;
import com.wuye.services.RoomServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("roomServiceManager")
public class RoomServiceManagerImpl extends BaseManagerImpl implements RoomServiceManager {
    @Autowired
    PropertyCompanyDao propertyCompanyDao;
    @Autowired
    PartyInfoDao partyInfoDao;
    @Autowired
    RoomDao roomDao;

    public RetVO save(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        //String type = (String) map.get("type");
        JSONArray rooms = (JSONArray) map.get("room");
        if (rooms != null && rooms.size() > 0) {
            for (int i = 0; i < rooms.size(); i++) {
                JSONObject srcRoom = rooms.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcRoom.get("roomId"))) {
                    // 新增
                    this.add(srcRoom);
                } else {
                    // 更改
                    this.update(srcRoom);
                }
            }
        }

        return retVo;
    }

    private void update(JSONObject srcRoom) {
        Room room = (Room) Room.getDefaultDao().getObject(Room.class, srcRoom.getInt("roomId"));

        Building building = (Building) Building.getDao().getObject(Building.class,
                srcRoom.getJSONObject("building").getInt("buildingId"));

        EntityCopyUtil.populate(room, srcRoom, null);
        room.setBuilding(building);
        room.save();
        if (srcRoom.has("partyInfo") && !srcRoom.getJSONObject("partyInfo").isEmpty()) {
            PartyInfo ownerInfo = room.getOwnerInfo();
            if (ownerInfo == null) {
                ownerInfo = new PartyInfo();
            }

            EntityCopyUtil.populate(ownerInfo,
                    srcRoom.getJSONObject("partyInfo"), null);
            User user = (User) ownerInfo.getObj();
            if (srcRoom.getJSONObject("partyInfo").has("user")
                    && !srcRoom.getJSONObject("partyInfo").getJSONObject("user").isEmpty()) {
                EntityCopyUtil.populate(user,
                        srcRoom.getJSONObject("partyInfo").getJSONObject("user"), null);
            }
            user.save();
            ownerInfo.save();
        }


    }

    private void add(JSONObject srcRoom) {
        // TODO Auto-generated method stub
        Room room = new Room();
        EntityCopyUtil.populate(room, srcRoom, null);
        Building building = (Building) Building.getDefaultDao().getObject(Building.class, srcRoom.getJSONObject("building").getInt("buildingId"));
        room.setBuilding(building);
        room.save();
        if (srcRoom.has("partyInfo") && !srcRoom.getJSONObject("partyInfo").isEmpty()) {
            JSONObject srcPartyInfo = srcRoom.getJSONObject("partyInfo");
            PartyInfo ownerInfo = new PartyInfo();
            EntityCopyUtil.populate(ownerInfo,
                    srcPartyInfo, null);
            if (srcPartyInfo.has("user") && !srcPartyInfo.getJSONObject("user").isEmpty()) {
                JSONObject srcUser = srcPartyInfo.getJSONObject("user");
                User ownerUser = new User();
                ownerUser.setUserType(BaseConstants.USER_TYPE_12);
                ownerUser.setAccount(srcUser.getString("account"));
                ownerUser.setStatusCd(BaseConstants.STATUS_NOT_EFF);
                ownerUser.save();
                ownerInfo.setObj(ownerUser);
            }
            RoomPartyRel roomPartyRel = new RoomPartyRel();
            roomPartyRel.setPartyInfo(ownerInfo);
            roomPartyRel.setRoom(room);
            roomPartyRel.setRelType(BaseConstants.ROOM_PARTY_REL_TYPE_10);
            roomPartyRel.save();
        }
    }

    public PartyInfo getPartyInfo(Integer id) {
        // TODO Auto-generated method stub
        return partyInfoDao.getPartyInfo(BaseConstants.CLASS_ROOM, id);
    }

    public PageInfo getRoomList(
            Map<String, Object> map, final int currentPage, final int perPageNum) {
        StringBuffer sql = new StringBuffer();
        List params = new ArrayList();
        sql.append("");
        sql.append("select a  from Room a where 1=1 ");
        if (map != null && !StrUtil.isNullOrEmpty(map.get("roomName"))) {
            sql.append("and  a.roomName  like ? ");
            params.add("%" + map.get("roomName") + "%");
        }
        if (map != null && !StrUtil.isNullOrEmpty(map.get("roomId"))) {
            sql.append("and  a.roomId=? ");
            params.add(map.get("roomId"));
        }
        if (map != null && !StrUtil.isNullOrEmpty(map.get("buildingId"))) {
            sql.append("and  a.building.buildingId=? ");
            params.add(map.get("buildingId"));
        }
        PageInfo pageInfo = this.dao.findPageInfoByHQLAndParams(sql.toString(), params, currentPage, perPageNum);
        List list = new ArrayList();
        if (!map.containsKey("convert")) {
            if (pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0) {
                for (int i = 0; i < pageInfo.getDataList().size(); i++) {
                    JSONObject jsObj = new JSONObject();
                    JSONObject destRoom = new JSONObject();
                    Room curRoom = (Room) pageInfo.getDataList().get(i);
                    EntityCopyUtil.populate(destRoom, curRoom, null);
                    JSONObject partyInfo = new JSONObject();
                    PartyInfo partyInfoSrc = null;
                    if (curRoom.getRoomPartyRels() != null && curRoom.getRoomPartyRels().size() > 0) {
                        List<RoomPartyRel> listRoom = new ArrayList<RoomPartyRel>(curRoom.getRoomPartyRels());
                        RoomPartyRel roomPartyRel = listRoom.get(0);
                        partyInfoSrc = roomPartyRel.getPartyInfo();
                        EntityCopyUtil.populate(partyInfo, partyInfoSrc, null);
                    }
                    JSONObject user = new JSONObject();
                    User userobj = (User) partyInfoSrc.getObj();
                    EntityCopyUtil.populate(user, userobj, null);
                    destRoom.put("partyInfo", partyInfo);
                    destRoom.put("user", user);
                    JSONObject buildingtype = new JSONObject();
                    JSONObject building = new JSONObject();
                    EntityCopyUtil.populate(buildingtype, curRoom.getBuildingType(), null);
                    EntityCopyUtil.populate(building, curRoom.getBuilding(), null);
                    destRoom.put("buildingType", buildingtype);
                    list.add(destRoom);
                }
            }
            pageInfo.setDataList(list);
        }
        return pageInfo;
    }

    public void removeRoom(Map<String, Object> map) throws Exception {
        if (map != null && map.containsKey("companyIdArray")) {
            if (map.containsKey("companyIdArray")) {
                JSONArray json = (JSONArray) map.get("companyIdArray");
                for (int i = 0; i < json.size(); i++) {
                    int companyId = json.getInt(i);
                    PropertyCompany comp = (PropertyCompany) this.getObject(PropertyCompany.class, companyId);
                    if (comp != null) {
                        comp.remove();
                    }
                }
            }

        }
    }

    public RetVO getRoom(Map<String, Object> map, int currentPage,
                         int perPageNum) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = roomDao.getRoom(map, currentPage, perPageNum);
        List<Object> dataList = pg.getDataList();
        JSONArray jsArray = new JSONArray();
        Map<String, Object> out = new HashMap<String, Object>();
        if (dataList != null && dataList.size() > 0) {
            for (Object obj : dataList) {
                Room room = (Room) obj;
                JSONObject jsObj = EntityCopyUtil.getJSONObject(room, null);
                Building building = room.getBuilding();
                Building buildingUp = null;
                if (building.getOwnerBuildingEntt() != null) {
                    buildingUp = building.getOwnerBuildingEntt();
                    JSONObject jsUnit = EntityCopyUtil.getJSONObject(building, new String[]{"buildingId", "buildingName"});
                    JSONObject jsBuilding = EntityCopyUtil.getJSONObject(buildingUp, new String[]{"buildingId", "buildingName"});
                    jsObj.put("building", jsBuilding);
                    jsObj.put("unit", jsUnit);
                } else {
                    JSONObject jsBuilding = EntityCopyUtil.getJSONObject(buildingUp, new String[]{"buildingId", "buildingName"});
                    jsObj.put("building", jsBuilding);
                }
                Community community = building.getCommunity();

                PropertyCompany company = community.getPropertyCompany();
                JSONObject jsCommunity = EntityCopyUtil.getJSONObject(community, new String[]{"communityId", "communityName"});
                jsCommunity.put("company", EntityCopyUtil.getJSONObject(company, new String[]{"companyId", "companyName"}));
                jsObj.put("community", jsCommunity);

                //取业主信息
                PartyInfo ownerInfo = room.getOwnerInfo();
                if (ownerInfo != null) {
                    JSONObject jsOwnerInfo = EntityCopyUtil.getJSONObject(ownerInfo, new String[]{"partyInfoId", "partyName"});
                    BaseEntity userEntt = ownerInfo.getObj();
                    if (userEntt instanceof User) {
                        User user = (User) userEntt;
                        jsOwnerInfo.put("userId", user.getUserId());
                        jsOwnerInfo.put("account", user.getAccount());
                    }
                    jsObj.put("ownerInfo", jsOwnerInfo);
                }

                jsArray.add(jsObj);

            }
            out.put("jsArray", jsArray);
        }
        out.put("pageCount", pg.getTotalPageCount());
        retVO.setObject(out);
        return retVO;
    }

    public RetVO addPartyInfos(Room room, JSONArray partryInfoArray) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        if (room == null || partryInfoArray == null || partryInfoArray.isEmpty()) {
            retVO.setResult(BaseConstants.RET_FALSE);
            retVO.setRetMsg("信息错误");
            return retVO;
        }
        for (int i = 0; i < partryInfoArray.size(); i++) {
            JSONObject srcPartyInfo = partryInfoArray.getJSONObject(i);
            PartyInfo ownerInfo = new PartyInfo();
            EntityCopyUtil.populate(ownerInfo, srcPartyInfo, null);
            RoomPartyRel roomPartyRel = new RoomPartyRel();
            roomPartyRel.setPartyInfo(ownerInfo);
            roomPartyRel.setRoom(room);
            roomPartyRel.setRelType(srcPartyInfo.get("relType") + "");
            roomPartyRel.save();
        }
        return retVO;
    }
}
