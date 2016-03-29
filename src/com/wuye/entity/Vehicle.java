package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Vehicle entity. @author MyEclipse Persistence Tools
 */

public class Vehicle extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer vehicleId;
    private String plateNbr;
    private Set vehicleRels = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Vehicle() {
    }

    /**
     * full constructor
     */
    public Vehicle(String plateNbr, Timestamp createDate, Timestamp updateDate,
                   String statusCd, Timestamp statusDate, Set vehicleRels) {
        this.plateNbr = plateNbr;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.vehicleRels = vehicleRels;
    }

    // Property accessors

    public Integer getVehicleId() {
        return super.getId();
    }

    public void setVehicleId(Integer vehicleId) {
        super.setId(vehicleId);
    }

    public String getPlateNbr() {
        return this.plateNbr;
    }

    public void setPlateNbr(String plateNbr) {
        this.plateNbr = plateNbr;
    }

    public Set getVehicleRels() {
        return this.vehicleRels;
    }

    public void setVehicleRels(Set vehicleRels) {
        this.vehicleRels = vehicleRels;
    }

}