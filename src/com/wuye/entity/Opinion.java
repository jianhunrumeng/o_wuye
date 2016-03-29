package com.wuye.entity;

import java.sql.Timestamp;

/**
 * Opinion entity. @author MyEclipse Persistence Tools
 */

public class Opinion extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer opinionId;
    private Integer parentOpinionId;
    private String title;
    private String conent;
    private Integer userId;
    private String classId;
    private Integer objId;


    // Constructors

    /**
     * default constructor
     */
    public Opinion() {
    }

    /**
     * minimal constructor
     */
    public Opinion(String title, Integer userId, Integer objId) {
        this.title = title;
        this.userId = userId;
        this.objId = objId;
    }

    /**
     * full constructor
     */
    public Opinion(Integer parentOpinionId, String title, String conent,
                   Integer userId, String classId, Integer objId,
                   Timestamp createDate, Timestamp updateDate, String statusCd,
                   Timestamp statusDate) {
        this.parentOpinionId = parentOpinionId;
        this.title = title;
        this.conent = conent;
        this.userId = userId;
        this.classId = classId;
        this.objId = objId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getOpinionId() {
        return super.getId();
    }

    public void setOpinionId(Integer opinionId) {
        super.setId(opinionId);
    }

    public Integer getParentOpinionId() {
        return this.parentOpinionId;
    }

    public void setParentOpinionId(Integer parentOpinionId) {
        this.parentOpinionId = parentOpinionId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConent() {
        return this.conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

}