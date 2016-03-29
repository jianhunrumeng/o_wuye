package com.wuye.entity;

import java.sql.Timestamp;

/**
 * MonthlyBalanceDetail entity. @author MyEclipse Persistence Tools
 */

public class MonthlyBalanceDetail extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer monthlyDetailId;
    private MonthlyBalance monthlyBalance;
    private String feeName;
    private Float amount;


    // Constructors

    /**
     * default constructor
     */
    public MonthlyBalanceDetail() {
    }

    /**
     * minimal constructor
     */
    public MonthlyBalanceDetail(MonthlyBalance monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }

    /**
     * full constructor
     */
    public MonthlyBalanceDetail(MonthlyBalance monthlyBalance, String feeName,
                                Float amount, Timestamp createDate, Timestamp updateDate,
                                String statusCd, Timestamp statusDate) {
        this.monthlyBalance = monthlyBalance;
        this.feeName = feeName;
        this.amount = amount;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getMonthlyDetailId() {
        return super.getId();
    }

    public void setMonthlyDetailId(Integer monthlyDetailId) {
        super.setId(monthlyDetailId);
    }

    public MonthlyBalance getMonthlyBalance() {
        return this.monthlyBalance;
    }

    public void setMonthlyBalance(MonthlyBalance monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }

    public String getFeeName() {
        return this.feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

}