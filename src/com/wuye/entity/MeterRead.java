package com.wuye.entity;

import java.sql.Timestamp;

/**
 * MeterRead entity. @author MyEclipse Persistence Tools
 */

public class MeterRead extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer meterReadId;
    private MeterInst meterInst;
    private Float lastRead;
    private Float currentRead;
    private Float usage;
    private String month;
    private Float price;
    private Float fee;


    // Constructors

    /**
     * default constructor
     */
    public MeterRead() {
    }

    /**
     * minimal constructor
     */
    public MeterRead(MeterInst meterInst) {
        this.meterInst = meterInst;
    }

    /**
     * full constructor
     */
    public MeterRead(MeterInst meterInst, Float lastRead, Float currentRead,
                     Float usage, String month, Float price, Float fee,
                     Timestamp createDate, Timestamp updateDate, String statusCd,
                     Timestamp statusDate) {
        this.meterInst = meterInst;
        this.lastRead = lastRead;
        this.currentRead = currentRead;
        this.usage = usage;
        this.month = month;
        this.price = price;
        this.fee = fee;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getMeterReadId() {
        return super.getId();
    }

    public void setMeterReadId(Integer meterReadId) {
        super.setId(meterReadId);
    }

    public MeterInst getMeterInst() {
        return this.meterInst;
    }

    public void setMeterInst(MeterInst meterInst) {
        this.meterInst = meterInst;
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

    public Float getUsage() {
        return this.usage;
    }

    public void setUsage(Float usage) {
        this.usage = usage;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getFee() {
        return this.fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

}