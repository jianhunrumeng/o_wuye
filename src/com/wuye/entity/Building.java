package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.dao.BuildingDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Building entity. @author MyEclipse Persistence Tools
 */

public class Building extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer buildingId;
    private Community community;
    private PartyInfo partyInfo;
    private String buildingName;
    private String buildingType;
    private Integer ownerBuilding;
    private String phone;
    private Integer floorCount;
    private Float stairArea;
    private Float userableArea;
    private Float afforestArea;
    private String ownershipType;
    private String buildingStructure;
    private String upgradeCondition;
    private Date finishTime;
    private Building ownerBuildingEntt;
    private List<Building> units;
    private Set rooms = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Building() {
    }

    /**
     * minimal constructor
     */
    public Building(Community community, Integer ownerBuilding) {
        this.community = community;
        this.ownerBuilding = ownerBuilding;
    }

    /**
     * full constructor
     */
    public Building(Community community, PartyInfo partyInfo,
                    String buildingName, String buildingType, Integer ownerBuilding,
                    String phone, Integer floorCount, Float stairArea,
                    Float userableArea, Float afforestArea, String ownershipType,
                    String buildingStructure, String upgradeCondition,
                    Timestamp finishTime, Timestamp createDate, Timestamp updateDate,
                    String statusCd, Timestamp statusDate, Set rooms) {
        this.community = community;
        this.partyInfo = partyInfo;
        this.buildingName = buildingName;
        this.buildingType = buildingType;
        this.ownerBuilding = ownerBuilding;
        this.phone = phone;
        this.floorCount = floorCount;
        this.stairArea = stairArea;
        this.userableArea = userableArea;
        this.afforestArea = afforestArea;
        this.ownershipType = ownershipType;
        this.buildingStructure = buildingStructure;
        this.upgradeCondition = upgradeCondition;
        this.finishTime = finishTime;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.rooms = rooms;
    }

    // Property accessors

    public Integer getBuildingId() {
        return super.getId();
    }

    public void setBuildingId(Integer buildingId) {
        super.setId(buildingId);
    }

    public Community getCommunity() {
        return this.community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public PartyInfo getPartyInfo() {
        return this.partyInfo;
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingType() {
        return this.buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getOwnerBuilding() {
        return this.ownerBuilding;
    }

    public void setOwnerBuilding(Integer ownerBuilding) {
        this.ownerBuilding = ownerBuilding;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFloorCount() {
        return this.floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public Float getStairArea() {
        return this.stairArea;
    }

    public void setStairArea(Float stairArea) {
        this.stairArea = stairArea;
    }

    public Float getUserableArea() {
        return this.userableArea;
    }

    public void setUserableArea(Float userableArea) {
        this.userableArea = userableArea;
    }

    public Float getAfforestArea() {
        return this.afforestArea;
    }

    public void setAfforestArea(Float afforestArea) {
        this.afforestArea = afforestArea;
    }

    public String getOwnershipType() {
        return this.ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getBuildingStructure() {
        return this.buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getUpgradeCondition() {
        return this.upgradeCondition;
    }

    public void setUpgradeCondition(String upgradeCondition) {
        this.upgradeCondition = upgradeCondition;
    }

    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Set getRooms() {
        return this.rooms;
    }

    public void setRooms(Set rooms) {
        this.rooms = rooms;
    }

    public void setOwnerBuildingEntt(Building owner) {
        this.ownerBuildingEntt = owner;
    }

    public Building getOwnerBuildingEntt() {
        if (this.isLoaded("ownerBuildingEntt", this.ownerBuildingEntt)) {
            return this.ownerBuildingEntt;
        }
        this.ownerBuildingEntt = getDao().getOwnerBuildingEntt(this);
        this.Loaded("ownerBuildingEntt");
        return this.ownerBuildingEntt;
    }

    public List<Building> getUnits() {
        if (this.isLoaded("units", this.units)) {
            return this.units;
        }
        this.units = getDao().getUnits(this);
        this.Loaded("units");
        return this.units;
    }

    public void save() {
        if (this.getPartyInfo() != null) {
            this.getPartyInfo().save();
        }
        super.save();
    }

    public static BuildingDao getDao() {
        return (BuildingDao) SpringUtil.getBean("buildingDao");
    }
}