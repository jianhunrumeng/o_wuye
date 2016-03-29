package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.dao.PartyInfoDao;

import java.util.HashSet;
import java.util.Set;


/**
 * PartyInfo entity. @author MyEclipse Persistence Tools
 */

public class PartyInfo extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer partyInfoId;
    private Address address;
    private String partyName;
    private String classId;
    private Integer objId;
    private String certNbr;
    private String certType;
    private String mobile;
    private String homePhone;
    private String officePhone;
    private String fax;
    private String email;
    private String qq;
    private String weiXin;
    private String zipCode;//邮编
    private Set communities = new HashSet(0);
    private Set propertyCompanies = new HashSet(0);
    private Set buildings = new HashSet(0);
    private Set roomPartyRels = new HashSet(0);
    private BaseEntity obj;
    private String certTypeName;

    // Constructors

    public BaseEntity getObj() {
        if (this.isLoaded("obj", this.obj)) {
            return this.obj;
        }
        this.obj = this.getDao().getObj(this.classId, this.objId);
        this.Loaded("obj");
        return this.obj;
    }

    public void setObj(BaseEntity obj) {
        this.setObjId(obj.getId());
        this.setClassId(obj.getObjClassId());
        this.setObj(obj);
    }

    /**
     * default constructor
     */
    public PartyInfo() {
    }

    /**
     * minimal constructor
     */
    public PartyInfo(String partyName, String classId, Integer objId) {
        this.partyName = partyName;
        this.classId = classId;
        this.objId = objId;
    }

    /**
     * full constructor
     */
    public PartyInfo(Address address, String partyName, String classId,
                     Integer objId, String certNbr, String certType, String mobile,
                     String homePhone, String officePhone, String fax, String email,
                     String qq, String weiXin, Set communities, Set propertyCompanies,
                     Set buildings, Set roomPartyRels) {
        this.address = address;
        this.partyName = partyName;
        this.classId = classId;
        this.objId = objId;
        this.certNbr = certNbr;
        this.certType = certType;
        this.mobile = mobile;
        this.homePhone = homePhone;
        this.officePhone = officePhone;
        this.fax = fax;
        this.email = email;
        this.qq = qq;
        this.weiXin = weiXin;
        this.communities = communities;
        this.propertyCompanies = propertyCompanies;
        this.buildings = buildings;
        this.roomPartyRels = roomPartyRels;
    }

    // Property accessors

    public Integer getPartyInfoId() {
        return super.getId();
    }

    public void setPartyInfoId(Integer partyInfoId) {
        super.setId(partyInfoId);
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPartyName() {
        return this.partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    public String getCertNbr() {
        return this.certNbr;
    }

    public void setCertNbr(String certNbr) {
        this.certNbr = certNbr;
    }

    public String getCertType() {
        return this.certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return this.officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeiXin() {
        return this.weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set getCommunities() {
        return this.communities;
    }

    public void setCommunities(Set communities) {
        this.communities = communities;
    }

    public Set getPropertyCompanies() {
        return this.propertyCompanies;
    }

    public void setPropertyCompanies(Set propertyCompanies) {
        this.propertyCompanies = propertyCompanies;
    }

    public Set getBuildings() {
        return this.buildings;
    }

    public void setBuildings(Set buildings) {
        this.buildings = buildings;
    }

    public Set getRoomPartyRels() {
        return this.roomPartyRels;
    }

    public void setRoomPartyRels(Set roomPartyRels) {
        this.roomPartyRels = roomPartyRels;
    }

    public void remove() {
        if (this.getAddress() != null) {
            this.getAddress().remove();
        }

        super.remove();
    }

    public void save() {
        if (this.getAddress() != null) {
            this.getAddress().save();
        }

        super.save();
    }

    public static PartyInfoDao getDao() {
        // TODO Auto-generated method stub
        return (PartyInfoDao) SpringUtil.getBean("partyInfoDao");
    }

    public String getCertTypeName() {
        if (!StrUtil.isNullOrEmpty(this.getCertType())) {
            if ("10".equals(this.getCertType())) {
                this.certTypeName = "身份证";
            } else if ("11".equals(this.getCertType())) {
                this.certTypeName = "营业执照";
            } else {
                this.certTypeName = "未知";
            }
        } else {
            this.certTypeName = "";
        }

        return this.certTypeName;
    }
}