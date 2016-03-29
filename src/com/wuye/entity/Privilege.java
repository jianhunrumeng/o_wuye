package com.wuye.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer privilegeId;
    private String privilegeName;
    private String privilegeType; //101--�˵�Ȩ�� 102--����Ȩ��
    private String path;
    private Integer parentPrivilegeId;
    private String parentPrivilegeName;
    private String privilegeCode;
    private Set rolePrivileges = new HashSet(0);
    private Set userAuths = new HashSet(0);
    private List<Privilege> childPrivileges;
    // Constructors

    /**
     * default constructor
     */
    public Privilege() {
    }

    /**
     * minimal constructor
     */
    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    /**
     * full constructor
     */
    public Privilege(String privilegeName, String path,
                     Integer parentPrivilegeId, String privilegeCode, String statusCd,
                     Timestamp createDate, Timestamp statusDate, Timestamp updateDate,
                     Set rolePrivileges, Set userAuths) {
        this.privilegeName = privilegeName;
        this.path = path;
        this.parentPrivilegeId = parentPrivilegeId;
        this.privilegeCode = privilegeCode;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
        this.rolePrivileges = rolePrivileges;
        this.userAuths = userAuths;
    }

    // Property accessors

    public Integer getPrivilegeId() {
        return super.getId();
    }

    public void setPrivilegeId(Integer privilegeId) {
        super.setId(privilegeId);
    }

    public String getPrivilegeName() {
        return this.privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentPrivilegeId() {
        return this.parentPrivilegeId;
    }

    public void setParentPrivilegeId(Integer parentPrivilegeId) {
        this.parentPrivilegeId = parentPrivilegeId;
    }

    public String getPrivilegeCode() {
        return this.privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public Set getRolePrivileges() {
        return this.rolePrivileges;
    }

    public void setRolePrivileges(Set rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    public Set getUserAuths() {
        return this.userAuths;
    }

    public void setUserAuths(Set userAuths) {
        this.userAuths = userAuths;
    }

    public String getParentPrivilegeName() {
        String parentPrivilegeName = "";
        if (this.getParentPrivilegeId() != null && this.getParentPrivilegeId() > 0) {
            Privilege parentPrivilege = (Privilege) BaseEntity.getDefaultDao().getObject(Privilege.class, this.getParentPrivilegeId());
            if (parentPrivilege != null) {
                parentPrivilegeName = parentPrivilege.getPrivilegeName();
            }
        }
        return parentPrivilegeName;
    }

    public void setParentPrivilegeName(String parentPrivilegeName) {
        this.parentPrivilegeName = parentPrivilegeName;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public List<Privilege> getChildPrivileges() {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("from Privilege p where p.statusCd = ? and p.parentPrivilegeId = ? ");
        sql.append("and p.privilegeType = ? ");
        params.add("1000");
        params.add(this.getPrivilegeId());
        params.add("101");
        childPrivileges = BaseEntity.getDefaultDao().findListByHQLAndParams(sql.toString(), params);
        return childPrivileges;
    }
}