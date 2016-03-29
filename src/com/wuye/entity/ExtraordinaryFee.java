package com.wuye.entity;

import java.sql.Timestamp;

/**
 * ExtraordinaryFee entity. @author MyEclipse Persistence Tools
 */

public class ExtraordinaryFee extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer extraordinaryFeeId;
    private AcctItemType acctItemType;
    private Room room;
    private Float amount;
    private Integer userId;


    // Constructors

    /**
     * default constructor
     */
    public ExtraordinaryFee() {
    }

    /**
     * minimal constructor
     */
    public ExtraordinaryFee(Room room) {
        this.room = room;
    }

    /**
     * full constructor
     */
    public ExtraordinaryFee(AcctItemType acctItemType, Room room, Float amount,
                            Integer userId, Timestamp createDate, Timestamp updateDate,
                            String statusCd, Timestamp statusDate) {
        this.acctItemType = acctItemType;
        this.room = room;
        this.amount = amount;
        this.userId = userId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getExtraordinaryFeeId() {
        return super.getId();
    }

    public void setExtraordinaryFeeId(Integer extraordinaryFeeId) {
        super.setId(extraordinaryFeeId);
    }

    public AcctItemType getAcctItemType() {
        return this.acctItemType;
    }

    public void setAcctItemType(AcctItemType acctItemType) {
        this.acctItemType = acctItemType;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}