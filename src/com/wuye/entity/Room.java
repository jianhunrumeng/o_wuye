package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.RoomDao;
import com.wuye.services.RoomServiceManager;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class Room extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer roomId;
    private Building building;
    private BuildingType buildingType;
    private String roomNbr;
    private String floor;
    private String buildingStructure;
    private String housingOrientation;
    private String usingState;
    private String upgradeCondition;
    private Float housingFeeRate;
    private Timestamp upgradeStartDate;
    private Timestamp upgradeEndDate;
    private Timestamp housedDate;
    private Set extraordinaryFees = new HashSet(0);
    private Set vehicleRels = new HashSet(0);
    private Set roomPartyRels = new HashSet(0);
    /**
     * 业主
     */
    private RoomPartyRel ownerRel;

    /**
     * 业主信息
     */
    private PartyInfo ownerInfo;

    /**
     * default constructor
     */
    public Room() {
    }

    /**
     * minimal constructor
     */
    public Room(Building building, String roomNbr, String floor) {
        this.building = building;
        this.roomNbr = roomNbr;
        this.floor = floor;
    }

    /**
     * full constructor
     */
    public Room(Building building, BuildingType buildingType, String roomNbr,
                String floor, String buildingStructure, String housingOrientation,
                String usingState, String upgradeCondition, Float housingFeeRate,
                Timestamp upgradeStartDate, Timestamp upgradeEndDate,
                Timestamp housedDate, Set extraordinaryFees, Set vehicleRels,
                Set roomPartyRels) {
        this.building = building;
        this.buildingType = buildingType;
        this.roomNbr = roomNbr;
        this.floor = floor;
        this.buildingStructure = buildingStructure;
        this.housingOrientation = housingOrientation;
        this.usingState = usingState;
        this.upgradeCondition = upgradeCondition;
        this.housingFeeRate = housingFeeRate;
        this.upgradeStartDate = upgradeStartDate;
        this.upgradeEndDate = upgradeEndDate;
        this.housedDate = housedDate;
        this.extraordinaryFees = extraordinaryFees;
        this.vehicleRels = vehicleRels;
        this.roomPartyRels = roomPartyRels;
    }

    // Property accessors

    public Integer getRoomId() {
        return super.getId();
    }

    public void save() {

        if (this.getBuildingType() != null) {
            this.getBuildingType().save();
        }
        /*if(this.getRoomPartyRels()!=null&&this.getRoomPartyRels().size()>0){
			Iterator<RoomPartyRel> iterator=this.getRoomPartyRels().iterator();
			while(iterator.hasNext()){
				RoomPartyRel roomPartyRel=iterator.next();
				if(roomPartyRel.getPartyInfo()!=null){
					if(roomPartyRel.getPartyInfo().getObj()!=null){
						roomPartyRel.getPartyInfo().getObj().save();
						roomPartyRel.getPartyInfo().setObjId(roomPartyRel.getPartyInfo().getObj().getId());
					}
					roomPartyRel.getPartyInfo().save();
				}
				roomPartyRel.save();
			}
		}*/
        super.save();
    }

    public static RoomServiceManager getService() {
        return (RoomServiceManager) SpringUtil.getBean("roomServiceManager");
    }

    public void setRoomId(Integer roomId) {
        super.setId(roomId);
    }

    public Building getBuilding() {
        return this.building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public BuildingType getBuildingType() {
        return this.buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public String getRoomNbr() {
        return this.roomNbr;
    }

    public void setRoomNbr(String roomNbr) {
        this.roomNbr = roomNbr;
    }

    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuildingStructure() {
        return this.buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getHousingOrientation() {
        return this.housingOrientation;
    }

    public void setHousingOrientation(String housingOrientation) {
        this.housingOrientation = housingOrientation;
    }

    public String getUsingState() {
        return this.usingState;
    }

    public void setUsingState(String usingState) {
        this.usingState = usingState;
    }

    public String getUpgradeCondition() {
        return this.upgradeCondition;
    }

    public void setUpgradeCondition(String upgradeCondition) {
        this.upgradeCondition = upgradeCondition;
    }

    public Float getHousingFeeRate() {
        return this.housingFeeRate;
    }

    public void setHousingFeeRate(Float housingFeeRate) {
        this.housingFeeRate = housingFeeRate;
    }

    public Timestamp getUpgradeStartDate() {
        return this.upgradeStartDate;
    }

    public void setUpgradeStartDate(Timestamp upgradeStartDate) {
        this.upgradeStartDate = upgradeStartDate;
    }

    public Timestamp getUpgradeEndDate() {
        return this.upgradeEndDate;
    }

    public void setUpgradeEndDate(Timestamp upgradeEndDate) {
        this.upgradeEndDate = upgradeEndDate;
    }

    public Timestamp getHousedDate() {
        return this.housedDate;
    }

    public void setHousedDate(Timestamp housedDate) {
        this.housedDate = housedDate;
    }

    public Set getExtraordinaryFees() {
        return this.extraordinaryFees;
    }

    public void setExtraordinaryFees(Set extraordinaryFees) {
        this.extraordinaryFees = extraordinaryFees;
    }

    public Set getVehicleRels() {
        return this.vehicleRels;
    }

    public void setVehicleRels(Set vehicleRels) {
        this.vehicleRels = vehicleRels;
    }

    public Set getRoomPartyRels() {
        return this.roomPartyRels;
    }

    public void setRoomPartyRels(Set roomPartyRels) {
        this.roomPartyRels = roomPartyRels;
    }

    public RoomPartyRel getOwnerRel() {
        if (this.isLoaded("ownerRel", this.ownerRel)) {
            return this.ownerRel;
        }
        this.ownerRel = Room.getDao().getOwnerRel(this);
        this.Loaded("ownerRel");
        return this.ownerRel;
    }

    public void setOwnerInfo(PartyInfo partyInfo) {
        if (this.getOwnerRel() == null) {
            this.ownerRel = new RoomPartyRel();
        }
        this.ownerRel.setRelType(BaseConstants.ROOM_PARTY_REL_TYPE_10);
        this.ownerRel.setRoom(this);
        this.ownerRel.setPartyInfo(partyInfo);
    }

    public PartyInfo getOwnerInfo() {
        if (this.isLoaded("ownerInfo", this.ownerInfo)) {
            return this.ownerInfo;
        }

        if (this.getOwnerRel() != null) {
            this.ownerInfo = this.getOwnerRel().getPartyInfo();
        } else {
            this.ownerInfo = null;
        }
        this.Loaded("ownerInfo");
        return this.ownerInfo;
    }

    public static RoomDao getDao() {
        return (RoomDao) SpringUtil.getBean("roomDao");
    }
}