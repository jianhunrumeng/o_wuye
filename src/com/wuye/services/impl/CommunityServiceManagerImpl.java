package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.CommunityDao;
import com.wuye.dao.PartyInfoDao;
import com.wuye.entity.*;
import com.wuye.services.CommunityServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("communityServiceManager")
public class CommunityServiceManagerImpl extends BaseManagerImpl implements
        CommunityServiceManager {
    @Autowired
    CommunityDao communityDao;
    @Autowired
    PartyInfoDao partyInfoDao;

    public void save(Community community) {
        // TODO Auto-generated method stub
        community.save();
    }

    public void save(List<Community> communitys) {
        for (Community community : communitys) {
            community.save();
        }

    }

    /**
     * 查询小区数量
     */
    public int getCommunityCount(Map<String, Object> map) {
        String hql = "select count(c.communityId) from Community c ";
        int recordCount = NumericUtil.nullToZero(communityDao
                .getSingleValueByHql(hql));
        int pageCount = 0;
        if (recordCount % 12 == 0 && recordCount != 0) {
            pageCount = recordCount / 12;
        } else {
            pageCount = recordCount / 12 + 1;
        }
        return pageCount;
    }

    public PageInfo getCommunityByName(String communityName, boolean fuzzy,
                                       final int currentPage, final int perPageNum) {
        return communityDao.getCommunityByName(communityName, fuzzy,
                currentPage, perPageNum);
    }

    public PageInfo getCommunityBySimpleName(String simpleName, boolean fuzzy,
                                             final int currentPage, final int perPageNum) {
        return communityDao.getCommunityBySimpleName(simpleName, fuzzy,
                currentPage, perPageNum);
    }

    public PartyInfo getPartyInfo(Integer communityId) {
        return partyInfoDao.getPartyInfo(BaseConstants.CLASS_COMMUNITY,
                communityId);
    }

    public RetVO getCommunity(Map<String, Object> map,
                              final int currentPage, final int perPageNum) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = communityDao.getCommunity(map, currentPage, perPageNum);
        List<Object> dataList = pg.getDataList();
        JSONArray jsArray = new JSONArray();
        if (dataList != null && dataList.size() > 0) {
            for (Object obj : dataList) {
                Community community = (Community) obj;
                PropertyCompany company = community.getPropertyCompany();
                JSONObject jsObj = EntityCopyUtil.getJSONObject(community, null);

                jsObj.put("propertyCompany", EntityCopyUtil.getJSONObject(company, new String[]{"companyId", "companyName"}));


                if (community.getCommunityInfo() != null) {
                    JSONObject communityInfo = EntityCopyUtil.getJSONObject(community.getCommunityInfo(), null);

                    Address srcAddr = community.getCommunityInfo().getAddress();
                    if (srcAddr != null) {
                        JSONObject addr = EntityCopyUtil.getJSONObject(srcAddr, null);
                        Area area = srcAddr.getArea();
                        Area city = area.getUpArea();
                        Area province = city.getUpArea();
                        JSONObject jsarea = EntityCopyUtil.getJSONObject(area, null);
                        JSONObject jsCity = EntityCopyUtil.getJSONObject(city, null);
                        JSONObject jsProvince = EntityCopyUtil.getJSONObject(province, null);
                        jsCity.put("upArea", jsProvince);
                        jsarea.put("upArea", jsCity);
                        addr.put("area", jsarea);
                        communityInfo.put("address", addr);
                    }

                    jsObj.put("communityInfo", communityInfo);
                }


                JSONObject partyInfo = new JSONObject();
                EntityCopyUtil.populate(partyInfo, community.getPartyInfo(), null);
                jsObj.put("partyInfo", partyInfo);
                jsArray.add(jsObj);

            }
            retVO.setObject(jsArray);
        }
        return retVO;
    }

    public RetVO getCommunitySimple(Map<String, Object> map) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append(" from Community c ")
                .append(" where 1=1 ")
                .append(" and c.statusCd = ? ");
        param.add(BaseConstants.STATUS_VALID);
        String communityName = StrUtil.strnull(map.get("communityName"));
        if (!communityName.equals("")) {
            sql.append(" and (c.communityName like ? ")
                    .append(" 	or c.communityName like ? ) ");
            param.add("%" + communityName.toUpperCase() + "%");
            param.add("%" + communityName.toLowerCase() + "%");
        }
        Integer companyId = NumericUtil.nullToIntegerZero(map.get("companyId"));
        if (!companyId.equals(0)) {
            sql.append(" and c.companyId = ? ");
            param.add(companyId);
        }
        /*sql.append(" and limit ? ");
		param.add(BaseConstants.DEFAULT_SHOW_ITEM);*/
        List<Community> communities = communityDao.findListByHQLAndParams(sql.toString(), param, BaseConstants.DEFAULT_SHOW_ITEM);
        JSONArray jsArray = new JSONArray();
        if (communities != null && communities.size() > 0) {
            for (Community com : communities) {
                JSONObject jsObj = EntityCopyUtil.getJSONObject(com, new String[]{"communityId", "communityName", "regionWithSHQ"});
                jsArray.add(jsObj);
            }
        }
        retVO.setObject(jsArray);
        return retVO;
    }

    public RetVO save(Map<String, Object> map) throws Exception {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        //String type = (String) map.get("type");
        JSONArray communities = (JSONArray) map.get("community");
        if (communities != null && communities.size() > 0) {
            for (int i = 0; i < communities.size(); i++) {
                JSONObject srcCommunity = communities.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcCommunity.get("communityId"))) {
                    // 新增
                    this.add(srcCommunity);
                } else {
                    // 更改
                    this.update(srcCommunity);
                }
            }
        }

        return retVo;
    }

    private RetVO add(JSONObject srcCommunity) {
        Community community = new Community();
        Organization organization = new Organization();
        PartyInfo communityInfo = new PartyInfo();
        PartyInfo leaderInfo = new PartyInfo();
        Address add = new Address();
        PropertyCompany propertyCompany = (PropertyCompany) PropertyCompany.getDao().getObject(PropertyCompany.class,
                srcCommunity.getJSONObject("propertyCompany").getInt("companyId"));

        EntityCopyUtil.populate(community, srcCommunity, null);
        EntityCopyUtil.populate(communityInfo,
                srcCommunity.getJSONObject("partyInfopp"), null);
        EntityCopyUtil.populate(leaderInfo,
                srcCommunity.getJSONObject("partyInfo"), null);
        EntityCopyUtil.populate(add, srcCommunity.getJSONObject("partyInfopp")
                .getJSONObject("address"), null);

        Area area = (Area) Area.getDefaultDao().getObject(Area.class, srcCommunity.getJSONObject("partyInfopp")
                .getJSONObject("address").getInt("areaId"));
        add.setArea(area);
        communityInfo.setAddress(add);

        organization.setOrgName(community.getCommunityName());
        organization.setOrgType(BaseConstants.ORG_TYPE_COMMUNITY);

        community.setOrganization(organization);
        community.setCommunityInfo(communityInfo);
        community.setPropertyCompany(propertyCompany);
        community.setPartyInfo(leaderInfo);
        community.save();

        return null;
    }

    private RetVO update(JSONObject srcCommunity) {
        Community community = (Community) Community.getDao().getObject(Community.class, srcCommunity.getInt("communityId"));
        Organization organization = community.getOrganization();
        PartyInfo communityInfo = community.getCommunityInfo();
        PartyInfo leaderInfo = community.getPartyInfo();
        Address add = null;
        if (organization == null) {
            organization = new Organization();
        }
        if (communityInfo == null) {
            communityInfo = new PartyInfo();
            add = new Address();
        } else {
            add = communityInfo.getAddress();
            if (add == null) {
                add = new Address();
            }
        }
        if (leaderInfo == null) {
            leaderInfo = new PartyInfo();
        }
        PropertyCompany propertyCompany = (PropertyCompany) PropertyCompany.getDao().getObject(PropertyCompany.class,
                srcCommunity.getJSONObject("propertyCompany").getInt("companyId"));
        propertyCompany.getCompanyInfo();
        EntityCopyUtil.populate(community, srcCommunity, null);
        EntityCopyUtil.populate(communityInfo,
                srcCommunity.getJSONObject("partyInfopp"), null);
        EntityCopyUtil.populate(leaderInfo,
                srcCommunity.getJSONObject("partyInfo"), null);
        EntityCopyUtil.populate(add, srcCommunity.getJSONObject("partyInfopp")
                .getJSONObject("address"), null);

        Area area = (Area) Area.getDefaultDao().getObject(Area.class, srcCommunity.getJSONObject("partyInfopp")
                .getJSONObject("address").getInt("areaId"));
        add.setArea(area);
        communityInfo.setAddress(add);

        organization.setOrgName(community.getCommunityName());
        organization.setOrgType(BaseConstants.ORG_TYPE_COMMUNITY);

        community.setOrganization(organization);
        community.setCommunityInfo(communityInfo);
        community.setPropertyCompany(propertyCompany);
        community.setPartyInfo(leaderInfo);
        community.save();

        return null;
    }

    private RetVO del(JSONArray communitIds) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        for (Object communitId : communitIds) {
            Community community = (Community) communityDao.getObject(
                    Community.class, NumericUtil.nullToIntegerZero(communitId));
            community.remove();
        }
        return retVo;

    }

    public RetVO remove(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONArray communitIds = (JSONArray) map.get("community");
        for (Object communitId : communitIds) {
            Community community = (Community) communityDao.getObject(
                    Community.class, NumericUtil.nullToIntegerZero(communitId));
            community.remove();
        }
        return retVo;
    }

    public List<Community> queryCommunityByNameAndCompanyId(
            String communityName, int companyId) {
        return communityDao.queryCommunityByObj(communityName, companyId);
    }

    public RetVO getBuildingRoomsSimple(Map<String, Object> map) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");

        int communityId = NumericUtil.nullToIntegerZero(map.get("communityId"));
        Community community = (Community) Community.getDao().getObject(Community.class, communityId);
        JSONArray jsArray = new JSONArray();
        if (community != null) {

            Set<Building> buildingSet = community.getBuildings();
            if (buildingSet != null && buildingSet.size() > 0) {
                Iterator iterator = buildingSet.iterator();
                while (iterator.hasNext()) {
                    Building building = (Building) iterator.next();
                    Building ownerBuilding = building.getOwnerBuildingEntt();

                    if (ownerBuilding == null) {
                        //楼栋处理
                        JSONObject buildingJson = new JSONObject();
                        buildingJson.put("buildingId", building.getId());
                        buildingJson.put("buildingName", building.getBuildingName());

                        List<Building> units = building.getUnits();
                        if (units != null && units.size() > 0) {
                            JSONArray unitsJSA = new JSONArray();
                            for (Building unit : units) {
                                // 单元
                                JSONObject unitJSO = new JSONObject();
                                unitJSO.put("buildingId", unit.getId());
                                unitJSO.put("buildingName",
                                        unit.getBuildingName());

                                // 单元下的房间
                                Set<Room> rooms = unit.getRooms();
                                if (rooms != null && rooms.size() > 0) {
                                    // 房间
                                    JSONArray roomsJSA = new JSONArray();
                                    Iterator itR = rooms.iterator();
                                    while (itR.hasNext()) {
                                        Room room = (Room) itR.next();
                                        JSONObject roomJSO = new JSONObject();
                                        roomJSO.put("roomId", room.getId());
                                        roomJSO.put("roomNbr",
                                                room.getRoomNbr());
                                        roomsJSA.add(roomJSO);
                                    }
                                    unitJSO.put("room", roomsJSA);
                                }
                                unitsJSA.add(unitJSO);

                                buildingJson.put("unit", unitsJSA);
                            }
                        } else {
                            //无单元
                            Set<Room> rooms = building.getRooms();
                            if (rooms != null && rooms.size() > 0) {
                                //房间
                                JSONArray roomsJSA = new JSONArray();
                                Iterator itR = rooms.iterator();
                                while (itR.hasNext()) {
                                    Room room = (Room) itR.next();
                                    JSONObject roomJSO = new JSONObject();
                                    roomJSO.put("roomId", room.getId());
                                    roomJSO.put("roomNbr", room.getRoomNbr());
                                    roomsJSA.add(roomJSO);
                                }
                                JSONArray unitJSA = new JSONArray();
                                JSONObject unitJSO = new JSONObject();
                                unitJSO.put("buildingId", building.getId());
                                unitJSO.put("buildingName", building.getBuildingName());
                                unitJSO.put("room", roomsJSA);
                                unitJSA.add(unitJSO);
                                buildingJson.put("unit", unitJSA);
                            }
                        }

                        jsArray.add(buildingJson);
                    }
                }
            }

        }
        retVO.setObject(jsArray);
        return retVO;
    }
}
