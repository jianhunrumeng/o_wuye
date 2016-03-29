package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AccountRecord entity. @author MyEclipse Persistence Tools
 */

public class AccountRecord extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer accountRecordId;
    private Account account;
    private Float amount;
    private Float lastBalance;
    private String remark;
    private Set payRecords = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public AccountRecord() {
    }

    /**
     * minimal constructor
     */
    public AccountRecord(Account account) {
        this.account = account;
    }

    /**
     * full constructor
     */
    public AccountRecord(Account account, Float amount, Float lastBalance,
                         String remark, Timestamp createDate, Timestamp updateDate,
                         String statusCd, Timestamp statusDate, Set payRecords) {
        this.account = account;
        this.amount = amount;
        this.lastBalance = lastBalance;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.payRecords = payRecords;
    }

    // Property accessors

    public Integer getAccountRecordId() {
        return super.getId();
    }

    public void setAccountRecordId(Integer accountRecordId) {
        super.setId(accountRecordId);
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getLastBalance() {
        return this.lastBalance;
    }

    public void setLastBalance(Float lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getPayRecords() {
        return this.payRecords;
    }

    public void setPayRecords(Set payRecords) {
        this.payRecords = payRecords;
    }

}