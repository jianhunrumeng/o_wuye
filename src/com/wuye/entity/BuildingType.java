package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * BuildingType entity. @author MyEclipse Persistence Tools
 */

public class BuildingType extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer buildingTypeId;
    private String buildingTypeName;
    private Integer communityId;
    private Float buildingArea;
    private Float comprisingArea;
    private Float publicArea;
    private Float costArea;
    private Set rooms = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public BuildingType() {
    }

    /**
     * full constructor
     */
    public BuildingType(Float buildingArea, Float comprisingArea,
                        Float publicArea, Float costArea, Timestamp createDate,
                        Timestamp updateDate, String statusCd, Timestamp statusDate,
                        Set rooms) {
        this.buildingArea = buildingArea;
        this.comprisingArea = comprisingArea;
        this.publicArea = publicArea;
        this.costArea = costArea;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.rooms = rooms;
    }

    // Property accessors

    public Integer getBuildingTypeId() {
        return super.getId();
    }

    public void setBuildingTypeId(Integer buildingTypeId) {
        super.setId(buildingTypeId);
    }

    public Float getBuildingArea() {
        return this.buildingArea;
    }

    public void setBuildingArea(Float buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Float getComprisingArea() {
        return this.comprisingArea;
    }

    public void setComprisingArea(Float comprisingArea) {
        this.comprisingArea = comprisingArea;
    }

    public Float getPublicArea() {
        return this.publicArea;
    }

    public void setPublicArea(Float publicArea) {
        this.publicArea = publicArea;
    }

    public Float getCostArea() {
        return this.costArea;
    }

    public void setCostArea(Float costArea) {
        this.costArea = costArea;
    }

    public Set getRooms() {
        return this.rooms;
    }

    public void setRooms(Set rooms) {
        this.rooms = rooms;
    }

    public String getBuildingTypeName() {
        return this.buildingTypeName;
    }

    public Integer getCommunityId() {
        return this.communityId;
    }

    public void setBuildingTypeName(String buildingTypeName) {
        this.buildingTypeName = buildingTypeName;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}