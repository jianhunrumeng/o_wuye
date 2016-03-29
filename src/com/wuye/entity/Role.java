package com.wuye.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer roleId;
    private String roleName;
    private Set userAuths = new HashSet(0);
    private Set rolePrivileges = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Role() {
    }

    /**
     * minimal constructor
     */
    public Role(String roleName, String statusCd) {
        this.roleName = roleName;
        this.statusCd = statusCd;
    }

    /**
     * full constructor
     */
    public Role(String roleName, String statusCd, Timestamp createDate,
                Timestamp statusDate, Timestamp updateDate, Set userAuths,
                Set rolePrivileges) {
        this.roleName = roleName;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
        this.userAuths = userAuths;
        this.rolePrivileges = rolePrivileges;
    }

    // Property accessors

    public Integer getRoleId() {
        return super.getId();
    }

    public void setRoleId(Integer roleId) {
        super.setId(roleId);
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set getUserAuths() {
        return this.userAuths;
    }

    public void setUserAuths(Set userAuths) {
        this.userAuths = userAuths;
    }

    public Set getRolePrivileges() {
        return this.rolePrivileges;
    }

    public void setRolePrivileges(Set rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

}