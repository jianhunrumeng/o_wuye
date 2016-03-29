package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer accountId;
    private Float balance;
    private String classId;
    private Integer objId;
    private Set accountRecords = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Account() {
    }

    /**
     * minimal constructor
     */
    public Account(Integer objId) {
        this.objId = objId;
    }

    /**
     * full constructor
     */
    public Account(Float balance, String classId, Integer objId,
                   Timestamp createDate, Timestamp updateDate, String statusCd,
                   Timestamp statusDate, Set accountRecords) {
        this.balance = balance;
        this.classId = classId;
        this.objId = objId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.accountRecords = accountRecords;
    }

    // Property accessors

    public Integer getAccountId() {
        return super.getId();
    }

    public void setAccountId(Integer accountId) {
        super.setId(accountId);
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
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

    public Set getAccountRecords() {
        return this.accountRecords;
    }

    public void setAccountRecords(Set accountRecords) {
        this.accountRecords = accountRecords;
    }

}