package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Parking entity. @author MyEclipse Persistence Tools
 */

public class Parking extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer parkingId;
    private Integer communityId;
    private ParkingType parkingType;
    private String parkingNbr;
    private String parkingPosition;
    private Set vehicleRels = new HashSet(0);

    /**
     * 车位类型关联的小区
     */
    private Community community;
    // Constructors

    /**
     * default constructor
     */
    public Parking() {
    }

    /**
     * minimal constructor
     */
    public Parking(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    /**
     * full constructor
     */
    public Parking(ParkingType parkingType, String parkingNbr,
                   String parkingPosition, Timestamp createDate, Timestamp updateDate,
                   String statusCd, Timestamp statusDate, Set vehicleRels) {
        this.parkingType = parkingType;
        this.parkingNbr = parkingNbr;
        this.parkingPosition = parkingPosition;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.vehicleRels = vehicleRels;
    }

    // Property accessors

    public Integer getParkingId() {
        return super.getId();
    }

    public void setParkingId(Integer parkingId) {
        super.setId(parkingId);
    }

    public ParkingType getParkingType() {
        return this.parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingNbr() {
        return this.parkingNbr;
    }

    public void setParkingNbr(String parkingNbr) {
        this.parkingNbr = parkingNbr;
    }

    public String getParkingPosition() {
        return this.parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition;
    }

    public Set getVehicleRels() {
        return this.vehicleRels;
    }

    public void setVehicleRels(Set vehicleRels) {
        this.vehicleRels = vehicleRels;
    }

    public Community getCommunity() {
        if (this.isLoaded("community", this.community)) {
            return this.community;
        }
        this.community = (Community) this.getDefaultDao().getObject(Community.class, this.getCommunityId());
        this.Loaded("community");
        return this.community;
    }

    public Integer getCommunityId() {
        return this.communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}