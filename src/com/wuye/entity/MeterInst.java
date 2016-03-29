package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MeterInst entity. @author MyEclipse Persistence Tools
 */

public class MeterInst extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer meterInstId;
    private MeterSpec meterSpec;
    private Float price;
    private String classId;
    private Integer objId;
    private Short floor;
    private String elevator;
    private Float lastRead;
    private Float currentRead;
    private String shareChargeType;
    private Set meterReads = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public MeterInst() {
    }

    /**
     * minimal constructor
     */
    public MeterInst(MeterSpec meterSpec) {
        this.meterSpec = meterSpec;
    }

    /**
     * full constructor
     */
    public MeterInst(MeterSpec meterSpec, Float price, String classId,
                     Integer objId, Short floor, String elevator, Float lastRead,
                     Float currentRead, String shareChargeType, Timestamp createDate,
                     Timestamp updateDate, String statusCd, Timestamp statusDate,
                     Set meterReads) {
        this.meterSpec = meterSpec;
        this.price = price;
        this.classId = classId;
        this.objId = objId;
        this.floor = floor;
        this.elevator = elevator;
        this.lastRead = lastRead;
        this.currentRead = currentRead;
        this.shareChargeType = shareChargeType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.meterReads = meterReads;
    }

    // Property accessors

    public Integer getMeterInstId() {
        return super.getId();
    }

    public void setMeterInstId(Integer meterInstId) {
        super.setId(meterInstId);
    }

    public MeterSpec getMeterSpec() {
        return this.meterSpec;
    }

    public void setMeterSpec(MeterSpec meterSpec) {
        this.meterSpec = meterSpec;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getObjId() {
        return this.objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public Short getFloor() {
        return this.floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public String getElevator() {
        return this.elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public Float getLastRead() {
        return this.lastRead;
    }

    public void setLastRead(Float lastRead) {
        this.lastRead = lastRead;
    }

    public Float getCurrentRead() {
        return this.currentRead;
    }

    public void setCurrentRead(Float currentRead) {
        this.currentRead = currentRead;
    }

    public String getShareChargeType() {
        return this.shareChargeType;
    }

    public void setShareChargeType(String shareChargeType) {
        this.shareChargeType = shareChargeType;
    }

    public Set getMeterReads() {
        return this.meterReads;
    }

    public void setMeterReads(Set meterReads) {
        this.meterReads = meterReads;
    }

}