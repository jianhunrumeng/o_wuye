package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer noticeId;
    private String noticeType;
    private String title;
    private String content;
    private Integer createStaff;
    private Set noticeRels = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Notice() {
    }

    /**
     * minimal constructor
     */
    public Notice(Integer createStaff) {
        this.createStaff = createStaff;
    }

    /**
     * full constructor
     */
    public Notice(String noticeType, String title, String content,
                  Integer createStaff, Timestamp createDate, Timestamp updateDate,
                  String statusCd, Timestamp statusDate, Set noticeRels) {
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.createStaff = createStaff;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.noticeRels = noticeRels;
    }

    // Property accessors

    public Integer getNoticeId() {
        return super.getId();
    }

    public void setNoticeId(Integer noticeId) {
        super.setId(noticeId);
    }

    public String getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
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

    public Integer getCreateStaff() {
        return this.createStaff;
    }

    public void setCreateStaff(Integer createStaff) {
        this.createStaff = createStaff;
    }

    public Set getNoticeRels() {
        return this.noticeRels;
    }

    public void setNoticeRels(Set noticeRels) {
        this.noticeRels = noticeRels;
    }

}