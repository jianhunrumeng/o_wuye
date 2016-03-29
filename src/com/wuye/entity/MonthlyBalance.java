package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MonthlyBalance entity. @author MyEclipse Persistence Tools
 */

public class MonthlyBalance extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer monthlyBalanceId;
    private Community community;
    private String classId;
    private Integer objId;
    private String month;
    private Float sum;
    private Timestamp surchargeStartDate;
    private Float surchargeRate;
    private Set monthlyBalanceDetails = new HashSet(0);
    private Set payRecords = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public MonthlyBalance() {
    }

    /**
     * minimal constructor
     */
    public MonthlyBalance(Community community) {
        this.community = community;
    }

    /**
     * full constructor
     */
    public MonthlyBalance(Community community, String classId, Integer objId,
                          String month, Float sum, Timestamp surchargeStartDate,
                          Float surchargeRate, Timestamp createDate, Timestamp updateDate,
                          String statusCd, Timestamp statusDate, Set monthlyBalanceDetails,
                          Set payRecords) {
        this.community = community;
        this.classId = classId;
        this.objId = objId;
        this.month = month;
        this.sum = sum;
        this.surchargeStartDate = surchargeStartDate;
        this.surchargeRate = surchargeRate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.monthlyBalanceDetails = monthlyBalanceDetails;
        this.payRecords = payRecords;
    }

    // Property accessors

    public Integer getMonthlyBalanceId() {
        return super.getId();
    }

    public void setMonthlyBalanceId(Integer monthlyBalanceId) {
        super.setId(monthlyBalanceId);
    }

    public Community getCommunity() {
        return this.community;
    }

    public void setCommunity(Community community) {
        this.community = community;
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

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Float getSum() {
        return this.sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public Timestamp getSurchargeStartDate() {
        return this.surchargeStartDate;
    }

    public void setSurchargeStartDate(Timestamp surchargeStartDate) {
        this.surchargeStartDate = surchargeStartDate;
    }

    public Float getSurchargeRate() {
        return this.surchargeRate;
    }

    public void setSurchargeRate(Float surchargeRate) {
        this.surchargeRate = surchargeRate;
    }

    public Set getMonthlyBalanceDetails() {
        return this.monthlyBalanceDetails;
    }

    public void setMonthlyBalanceDetails(Set monthlyBalanceDetails) {
        this.monthlyBalanceDetails = monthlyBalanceDetails;
    }

    public Set getPayRecords() {
        return this.payRecords;
    }

    public void setPayRecords(Set payRecords) {
        this.payRecords = payRecords;
    }

}