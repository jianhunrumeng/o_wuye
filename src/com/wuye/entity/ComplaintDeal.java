package com.wuye.entity;

import java.sql.Timestamp;

/**
 * ComplaintDeal entity. @author MyEclipse Persistence Tools
 */

public class ComplaintDeal extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer complaintDealId;
    private Complaint complaint;
    private User user;
    private String result;


    // Constructors

    /**
     * default constructor
     */
    public ComplaintDeal() {
    }

    /**
     * full constructor
     */
    public ComplaintDeal(Complaint complaint, User user, String result,
                         Timestamp createDate, Timestamp updateDate, String statusCd,
                         Timestamp statusDate) {
        this.complaint = complaint;
        this.user = user;
        this.result = result;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getComplaintDealId() {
        return super.getId();
    }

    public void setComplaintDealId(Integer complaintDealId) {
        super.setId(complaintDealId);
    }

    public Complaint getComplaint() {
        return this.complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}