package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Complaint entity. @author MyEclipse Persistence Tools
 */

public class Complaint extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer complaintId;
    private String title;
    private String content;
    private Integer complainterId;
    private String complainter;
    private String complainterPhone;
    private Integer recorderId;
    private String recorder;
    private String recorderPhone;
    private String result;
    private String opinion;
    private String remark;
    private Set complaintDeals = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Complaint() {
    }

    /**
     * minimal constructor
     */
    public Complaint(String title, String result) {
        this.title = title;
        this.result = result;
    }

    /**
     * full constructor
     */
    public Complaint(String title, String content, Integer complainterId,
                     String complainter, String complainterPhone, Integer recorderId,
                     String recorder, String recorderPhone, String result,
                     String opinion, String remark, Timestamp createDate,
                     Timestamp updateDate, String statusCd, Timestamp statusDate,
                     Set complaintDeals) {
        this.title = title;
        this.content = content;
        this.complainterId = complainterId;
        this.complainter = complainter;
        this.complainterPhone = complainterPhone;
        this.recorderId = recorderId;
        this.recorder = recorder;
        this.recorderPhone = recorderPhone;
        this.result = result;
        this.opinion = opinion;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.complaintDeals = complaintDeals;
    }

    // Property accessors

    public Integer getComplaintId() {
        return super.getId();
    }

    public void setComplaintId(Integer complaintId) {
        super.setId(complaintId);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getComplainterId() {
        return this.complainterId;
    }

    public void setComplainterId(Integer complainterId) {
        this.complainterId = complainterId;
    }

    public String getComplainter() {
        return this.complainter;
    }

    public void setComplainter(String complainter) {
        this.complainter = complainter;
    }

    public String getComplainterPhone() {
        return this.complainterPhone;
    }

    public void setComplainterPhone(String complainterPhone) {
        this.complainterPhone = complainterPhone;
    }

    public Integer getRecorderId() {
        return this.recorderId;
    }

    public void setRecorderId(Integer recorderId) {
        this.recorderId = recorderId;
    }

    public String getRecorder() {
        return this.recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRecorderPhone() {
        return this.recorderPhone;
    }

    public void setRecorderPhone(String recorderPhone) {
        this.recorderPhone = recorderPhone;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getComplaintDeals() {
        return this.complaintDeals;
    }

    public void setComplaintDeals(Set complaintDeals) {
        this.complaintDeals = complaintDeals;
    }

}