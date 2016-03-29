package com.wuye.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ParkingType entity. @author MyEclipse Persistence Tools
 */

public class ParkingType extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer parkingTypeId;
    private String parkingTypeName;
    private Integer communityId;
    private String parkingType;
    private Float price;
    private String remark;
    private Set vehicleRels = new HashSet(0);
    private Set parkings = new HashSet(0);
    /**
     * 车位类型关联的小区
     */
    private Community community;
    // Constructors

    /**
     * default constructor
     */
    public ParkingType() {
    }

    /**
     * full constructor
     */
    public ParkingType(String parkingType, Float price, String remark,
                       Set vehicleRels, Set parkings) {
        this.parkingType = parkingType;
        this.price = price;
        this.remark = remark;
        this.vehicleRels = vehicleRels;
        this.parkings = parkings;
    }

    // Property accessors

    public Integer getParkingTypeId() {
        return super.getId();
    }

    public void setParkingTypeId(Integer parkingTypeId) {
        super.setId(parkingTypeId);
    }

    public String getParkingType() {
        return this.parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getVehicleRels() {
        return this.vehicleRels;
    }

    public void setVehicleRels(Set vehicleRels) {
        this.vehicleRels = vehicleRels;
    }

    public Set getParkings() {
        return this.parkings;
    }

    public void setParkings(Set parkings) {
        this.parkings = parkings;
    }

    public Community getCommunity() {
        if (this.isLoaded("community", this.community)) {
            return this.community;
        }
        this.community = (Community) this.getDefaultDao().getObject(Community.class, this.getCommunityId());
        this.Loaded("community");
        return this.community;
    }

    public String getParkingTypeName() {
        return this.parkingTypeName;
    }

    public Integer getCommunityId() {
        return this.communityId;
    }

    public void setParkingTypeName(String parkingTypeName) {
        this.parkingTypeName = parkingTypeName;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}