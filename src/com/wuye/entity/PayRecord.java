package com.wuye.entity;

import java.sql.Timestamp;

/**
 * PayRecord entity. @author MyEclipse Persistence Tools
 */

public class PayRecord extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer payRecordId;
    private MonthlyBalance monthlyBalance;
    private AccountRecord accountRecord;
    private Integer roomId;
    private Float amount;
    private String remark;
    private String payMethod;


    // Constructors

    /**
     * default constructor
     */
    public PayRecord() {
    }

    /**
     * minimal constructor
     */
    public PayRecord(MonthlyBalance monthlyBalance,
                     AccountRecord accountRecord, Integer roomId) {
        this.monthlyBalance = monthlyBalance;
        this.accountRecord = accountRecord;
        this.roomId = roomId;
    }

    /**
     * full constructor
     */
    public PayRecord(MonthlyBalance monthlyBalance,
                     AccountRecord accountRecord, Integer roomId, Float amount,
                     String remark, String payMethod, Timestamp createDate,
                     Timestamp updateDate, String statusCd, Timestamp statusDate) {
        this.monthlyBalance = monthlyBalance;
        this.accountRecord = accountRecord;
        this.roomId = roomId;
        this.amount = amount;
        this.remark = remark;
        this.payMethod = payMethod;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getPayRecordId() {
        return super.getId();
    }

    public void setPayRecordId(Integer payRecordId) {
        super.setId(payRecordId);
    }

    public MonthlyBalance getMonthlyBalance() {
        return this.monthlyBalance;
    }

    public void setMonthlyBalance(MonthlyBalance monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }

    public AccountRecord getAccountRecord() {
        return this.accountRecord;
    }

    public void setAccountRecord(AccountRecord accountRecord) {
        this.accountRecord = accountRecord;
    }

    public Integer getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

}