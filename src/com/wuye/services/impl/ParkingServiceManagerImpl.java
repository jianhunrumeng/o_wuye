package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.ParkingDao;
import com.wuye.entity.*;
import com.wuye.services.ParkingServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("parkingServiceManager")
public class ParkingServiceManagerImpl extends BaseManagerImpl implements
        ParkingServiceManager {
    @Autowired
    private ParkingDao parkingDao;

    public RetVO saveParkingType(Map<String, Object> map) throws Exception {
        // TODO Auto-generated method stub
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        //String type = (String) map.get("type");
        JSONArray parkingTypes = (JSONArray) map.get("parkingType");
        if (parkingTypes != null && parkingTypes.size() > 0) {
            for (int i = 0; i < parkingTypes.size(); i++) {
                JSONObject srcParkingType = parkingTypes.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcParkingType.get("parkingTypeId"))) {
                    // 新增
                    this.addParkingType(srcParkingType);
                } else {
                    // 更改
                    this.updateParkingType(srcParkingType);
                }
            }
        }

        return retVo;
    }

    private void updateParkingType(JSONObject srcParkingType) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        ParkingType parkingType = (ParkingType) ParkingType.getDefaultDao().getObject(ParkingType.class, srcParkingType.getInt("parkingTypeId"));

        Community community = (Community) Community.getDao().getObject(Community.class, srcParkingType.getJSONObject("community").getInt("communityId"));
        EntityCopyUtil.populate(parkingType, srcParkingType, null);
        parkingType.setCommunityId(community.getId());
        parkingType.save();
    }

    private void addParkingType(JSONObject srcParkingType) {
        // TODO Auto-generated method stub
        //Community community = (Community) Community.getDao().getObject(Community.class, srcParkingType.getInt("communityId"));
        ParkingType parkingType = new ParkingType();
        EntityCopyUtil.populate(parkingType, srcParkingType, null);
        Community community = (Community) Community.getDao().getObject(Community.class, srcParkingType.getJSONObject("community").getInt("communityId"));
        parkingType.setCommunityId(community.getId());
        parkingType.save();
    }

    public RetVO removeParkingType(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONArray parkingTypeIds = (JSONArray) map.get("parkingType");
        for (Object parkingTypeId : parkingTypeIds) {
            ParkingType parkingType = (ParkingType) ParkingType.getDefaultDao().getObject(
                    ParkingType.class, NumericUtil.nullToIntegerZero(parkingTypeId));
            parkingType.remove();
        }
        return retVo;
    }


    public RetVO getParkingType(Map<String, Object> map, int currentPage,
                                int perPageNum) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = parkingDao.getParkingType(map, currentPage, perPageNum);
        List<Object> dataList = pg.getDataList();
        JSONArray jsArray = new JSONArray();
        Map<String, Object> out = new HashMap<String, Object>();
        if (dataList != null && dataList.size() > 0) {
            for (Object obj : dataList) {
                ParkingType parkingType = (ParkingType) obj;
                Community community = parkingType.getCommunity();
                JSONObject jsObj = EntityCopyUtil.getJSONObject(parkingType, null);

                jsObj.put("community", EntityCopyUtil.getJSONObject(community, new String[]{"communityId", "communityName"}));

                jsArray.add(jsObj);

            }
            out.put("jsArray", jsArray);

        }
        out.put("pageCount", pg.getTotalPageCount());
        retVO.setObject(out);
        return retVO;
    }

    public RetVO saveParking(Map<String, Object> map) throws Exception {
        // TODO Auto-generated method stub
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        //String type = (String) map.get("type");
        JSONArray parkings = (JSONArray) map.get("parking");
        if (parkings != null && parkings.size() > 0) {
            for (int i = 0; i < parkings.size(); i++) {
                JSONObject srcParking = parkings.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcParking.get("parkingId"))) {
                    // 新增
                    this.addParking(srcParking);
                } else {
                    // 更改
                    this.updateParking(srcParking);
                }
            }
        }

        return retVo;
    }

    private void updateParking(JSONObject srcParking) {
        Parking parking = (Parking) Parking.getDefaultDao().getObject(Parking.class, srcParking.getInt("parkingId"));
        EntityCopyUtil.populate(parking, srcParking, null);
        ParkingType parkingType = (ParkingType) ParkingType.getDefaultDao().getObject(Parking.class, srcParking.getJSONObject("parkingType").getInt("parkingTypeId"));
        parking.setParkingType(parkingType);

        Set<VehicleRel> vhs = parking.getVehicleRels();
        VehicleRel vehicleRel = null;
        if (vhs != null && vhs.size() > 0) {
            vehicleRel = vhs.toArray(new VehicleRel[0])[0];
        }
        JSONObject srcVehicleRel = srcParking.getJSONObject("vehicleRel");
        if (srcVehicleRel != null && srcVehicleRel.getJSONObject("room") != null
                && srcVehicleRel.getJSONObject("room").get("roomId") != null) {
            Room room = (Room) Room.getDefaultDao().getObject(Room.class, NumericUtil.nullToIntegerZero(srcVehicleRel.getJSONObject("room").get("roomId")));
            if (room != null) {
                vehicleRel.setRoom(room);
                vehicleRel.setParking(parking);
                vehicleRel.setParkingType(parkingType);
                vehicleRel.setPlateNbr(srcVehicleRel.getString("plateNbr"));
                parking.setStatusCd(BaseConstants.STATUS_VALID);
                vehicleRel.save();
            }
        } else {
            if (vehicleRel != null) {
                vehicleRel.remove();
                parking.setStatusCd(BaseConstants.STATUS_NOT_EFF);
            }
        }
        parking.save();
    }

    private void addParking(JSONObject srcParking) {
        // TODO Auto-generated method stub
        Parking parking = new Parking();
        Community community = (Community) Community.getDao().getObject(Community.class, srcParking.getJSONObject("community").getInt("communityId"));
        parking.setCommunityId(community.getId());
        EntityCopyUtil.populate(parking, srcParking, null);
        ParkingType parkingType = (ParkingType) ParkingType.getDefaultDao().getObject(Parking.class, srcParking.getJSONObject("parkingType").getInt("parkingTypeId"));
        parking.setParkingType(parkingType);
        //默认空闲状态
        parking.setStatusCd(BaseConstants.STATUS_NOT_EFF);
        JSONObject srcVehicleRel = srcParking.getJSONObject("vehicleRel");
        if (srcVehicleRel != null && srcVehicleRel.getJSONObject("room") != null
                && srcVehicleRel.getJSONObject("room").get("roomId") != null) {
            Room room = (Room) Room.getDefaultDao().getObject(Room.class, NumericUtil.nullToIntegerZero(srcVehicleRel.getJSONObject("room").get("roomId")));
            if (room != null) {
                VehicleRel vehicleRel = new VehicleRel();
                vehicleRel.setRoom(room);
                vehicleRel.setParking(parking);
                vehicleRel.setParkingType(parkingType);
                vehicleRel.setPlateNbr(srcVehicleRel.getString("plateNbr"));
                vehicleRel.save();
                parking.setStatusCd(BaseConstants.STATUS_VALID);
            }
        }
        parking.save();
    }

    public RetVO removeParking(Map<String, Object> map) {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        JSONArray parkingIds = (JSONArray) map.get("parking");
        for (Object parkingId : parkingIds) {
            Parking parking = (Parking) Parking.getDefaultDao().getObject(Parking.class, NumericUtil.nullToIntegerZero(parkingId));
            Set<VehicleRel> vhs = parking.getVehicleRels();
            VehicleRel vehicleRel = null;
            if (vhs != null && vhs.size() > 0) {
                vehicleRel = vhs.toArray(new VehicleRel[0])[0];
            }
            if (vehicleRel != null) {
                vehicleRel.remove();
            }
            parking.remove();
        }
        return retVo;
    }

    public RetVO getParking(Map<String, Object> map, int currentPage,
                            int perPageNum) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = parkingDao.getParking(map, currentPage, perPageNum);
        List<Object> dataList = pg.getDataList();
        JSONArray jsArray = new JSONArray();
        Map<String, Object> out = new HashMap<String, Object>();
        if (dataList != null && dataList.size() > 0) {
            for (Object obj : dataList) {
                Parking parking = (Parking) obj;
                Community community = parking.getCommunity();
                JSONObject jsObj = EntityCopyUtil.getJSONObject(parking, new String[]{"parkingId", "parkingNbr", "parkingPosition"});

                jsObj.put("community", EntityCopyUtil.getJSONObject(community, new String[]{"communityId", "communityName"}));

                Set<VehicleRel> vhs = parking.getVehicleRels();
                if (vhs != null && vhs.size() > 0) {
                    VehicleRel vehicleRel = vhs.toArray(new VehicleRel[0])[0];
                    JSONObject vehicleRelJsobj = EntityCopyUtil.getJSONObject(vehicleRel, new String[]{"plateNbr"});
                    vehicleRelJsobj.put("room", EntityCopyUtil.getJSONObject(vehicleRel.getRoom(), new String[]{"roomId"}));

                    jsObj.put("vehicleRel", vehicleRelJsobj);
                }

                jsArray.add(jsObj);

            }
            out.put("jsArray", jsArray);
        }
        out.put("pageCount", pg.getTotalPageCount());
        retVO.setObject(out);
        return retVO;
    }

}
