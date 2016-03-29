package com.wuye.entity;

import java.sql.Timestamp;

/**
 * NoticeRel entity. @author MyEclipse Persistence Tools
 */

public class NoticeRel extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer noticeRelId;
    private Notice notice;
    private String classId;
    private Integer objId;
    private Integer communityId;

    // Constructors

    /**
     * default constructor
     */
    public NoticeRel() {
    }

    /**
     * minimal constructor
     */
    public NoticeRel(Notice notice, Integer objId) {
        this.notice = notice;
        this.objId = objId;
    }

    /**
     * full constructor
     */
    public NoticeRel(Notice notice, String classId, Integer objId,
                     Timestamp createDate, Timestamp updateDate, String statusCd,
                     Timestamp statusDate) {
        this.notice = notice;
        this.classId = classId;
        this.objId = objId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getNoticeRelId() {
        return super.getId();
    }

    public void setNoticeRelId(Integer noticeRelId) {
        super.setId(noticeRelId);
    }

    public Notice getNotice() {
        return this.notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
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

    public Integer getCommunityId() {
        return this.communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}