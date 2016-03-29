package com.wuye.entity;

import java.sql.Timestamp;

/**
 * UserOrgRel entity. @author MyEclipse Persistence Tools
 */

public class UserOrgRel extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer userOrgRelId;
    private Organization organization;
    private User user;


    // Constructors

    /**
     * default constructor
     */
    public UserOrgRel() {
    }

    /**
     * minimal constructor
     */
    public UserOrgRel(Organization organization, User user) {
        this.organization = organization;
        this.user = user;
    }

    /**
     * full constructor
     */
    public UserOrgRel(Organization organization, User user, String statusCd,
                      Timestamp createDate, Timestamp statusDate, Timestamp updateDate) {
        this.organization = organization;
        this.user = user;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
    }

    // Property accessors

    public Integer getUserOrgRelId() {
        return super.getId();
    }

    public void setUserOrgRelId(Integer userOrgRelId) {
        super.setId(userOrgRelId);
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}