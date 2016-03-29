package com.wuye.entity;

import java.sql.Timestamp;

/**
 * RolePrivilege entity. @author MyEclipse Persistence Tools
 */

public class RolePrivilege extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer rolePrivilegeId;
    private Integer roleId;
    private Integer privilegeId;
    private Role role;
    private Privilege privilege;


    // Constructors

    /**
     * default constructor
     */
    public RolePrivilege() {
    }

    /**
     * minimal constructor
     */
    public RolePrivilege(Role role, Privilege privilege) {
        this.role = role;
        this.privilege = privilege;
    }

    /**
     * full constructor
     */
    public RolePrivilege(Role role, Privilege privilege, String statusCd,
                         Timestamp createDate, Timestamp statusDate, Timestamp updateDate) {
        this.role = role;
        this.privilege = privilege;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
    }

    // Property accessors

    public Integer getRolePrivilegeId() {
        return super.getId();
    }

    public void setRolePrivilegeId(Integer rolePrivilegeId) {
        super.setId(rolePrivilegeId);
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privilege getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}