package com.wuye.entity;

import java.sql.Timestamp;

/**
 * UserAuth entity. @author MyEclipse Persistence Tools
 */

public class UserAuth extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer userAuthId;
    private User user;
    private Role role;
    //private Privilege privilege;


    // Constructors

    /**
     * default constructor
     */
    public UserAuth() {
    }

    /**
     * minimal constructor
     */
    public UserAuth(User user) {
        this.user = user;
    }

    /**
     * full constructor
     */
    public UserAuth(User user, Role role, String statusCd,
                    Timestamp createDate, Timestamp statusDate, Timestamp updateDate) {
        this.user = user;
        this.role = role;
        //this.privilege = privilege;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
    }

    // Property accessors

    public Integer getUserAuthId() {
        return super.getId();
    }

    public void setUserAuthId(Integer userAuthId) {
        super.setId(userAuthId);
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	/*public Privilege getPrivilege() {
        return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}*/

}