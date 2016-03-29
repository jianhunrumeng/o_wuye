package com.wuye.entity;

import com.wuye.common.exception.RtManagerException;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PropertyCompanyDao;
import com.wuye.services.PropertyCompanyServiceManager;

import java.util.HashSet;
import java.util.Set;

/**
 * PropertyCompany entity. @author MyEclipse Persistence Tools
 */

public class PropertyCompany extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer companyId;
    private Organization organization;
    private PartyInfo companyInfo;
    private String companyName;
    private String simpleName;
    private String bankAcctName;
    /**
     * 开户银行属性值ID
     */
    private String bankName;
    /**
     * 开户银行名称
     */
    private String bankName2;
    private String bankAcctNbr;
    private Set communities = new HashSet(0);
    private Set users = new HashSet(0);
    private PartyInfo partyInfo;
    /**
     * 封装物业公司地址的省市区名称，如海南省海口市龙华区
     */
    private String regionWithSHQ;
    // Constructors

    /**
     * default constructor
     */
    public PropertyCompany() {
    }

    /**
     * minimal constructor
     */
    public PropertyCompany(Organization organization, PartyInfo companyInfo) {
        this.organization = organization;
        this.companyInfo = companyInfo;
    }

    /**
     * full constructor
     */
    public PropertyCompany(Organization organization, PartyInfo companyInfo,
                           String companyName, String simpleName, String bankAcctName,
                           String bankName, String bankAcctNbr, Set communities) {
        this.organization = organization;
        this.companyInfo = companyInfo;
        this.companyName = companyName;
        this.simpleName = simpleName;
        this.bankAcctName = bankAcctName;
        this.bankName = bankName;
        this.bankAcctNbr = bankAcctNbr;
        this.communities = communities;
    }

    // Property accessors

    public Integer getCompanyId() {
        return super.getId();
    }

    public void setCompanyId(Integer companyId) {
        super.setId(companyId);
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public PartyInfo getCompanyInfo() {
        return this.companyInfo;
    }

    public void setCompanyInfo(PartyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getBankAcctName() {
        return this.bankAcctName;
    }

    public void setBankAcctName(String bankAcctName) {
        this.bankAcctName = bankAcctName;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAcctNbr() {
        return this.bankAcctNbr;
    }

    public void setBankAcctNbr(String bankAcctNbr) {
        this.bankAcctNbr = bankAcctNbr;
    }

    public Set getCommunities() {
        return this.communities;
    }

    public void setCommunities(Set communities) {
        this.communities = communities;
    }

    public void save() {
        if (this.getOrganization() != null) {
            this.getOrganization().save();
        }

        if (this.getCompanyInfo() != null) {
            this.getCompanyInfo().save();
        }
        super.save();
        if (this.getPartyInfo() != null) {

            this.getPartyInfo().setClassId(BaseConstants.CLASS_COMPANY);
            this.getPartyInfo().setObjId(this.getCompanyId());
            this.getPartyInfo().save();
        }
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
        if (partyInfo != null && partyInfo.getPartyInfoId() == null) {

            partyInfo.setClassId(BaseConstants.CLASS_COMPANY);
        }
    }

    public PartyInfo getPartyInfo() {
        if (this.isLoaded("partyInfo", this.partyInfo)) {
            return this.partyInfo;
        }
        this.partyInfo = PropertyCompany.getService().getPartyInfo(this.getId());
        this.Loaded("partyInfo");
        return this.partyInfo;
    }

    public static PropertyCompanyDao getDao() {
        return (PropertyCompanyDao) SpringUtil.getBean("propertyCompanyDao");
    }

    public static PropertyCompanyServiceManager getService() {
        return (PropertyCompanyServiceManager) SpringUtil.getBean("propertyCompanyServiceManager");
    }

    public void remove() {
        //判断是否有小区
        String hql = "SELECT 1 FROM DUAL WHERE EXISTS ( SELECT 1 FROM community c WHERE c.company_id = ? ) ";
        int count = super.getDefaultDao().jdbcQueryForInt(hql, this.getId());
        if (count > 0) {
            throw new RtManagerException("请先删除物业公司下所有小区数据再删除物业公司");
        }
        super.remove();
        if (this.getCompanyInfo() != null) {
            this.getCompanyInfo().getPropertyCompanies().remove(this);
            this.getCompanyInfo().remove();
        }

        if (this.getOrganization() != null) {
            this.getOrganization().getPropertyCompanies().remove(this);
            this.getOrganization().remove();
        }

        if (this.getPartyInfo() != null) {
            this.getPartyInfo().remove();
        }

    }

    /**
     * 获取省市区
     *
     * @return
     */
    public String getRegionWithSHQ() {
        if (this.isLoaded("regionWithSHQ", this.regionWithSHQ)) {
            return this.regionWithSHQ;
        }
        if (this.getCompanyInfo() != null) {
            Address add = this.getCompanyInfo().getAddress();
            if (add != null) {
                Area area = add.getArea();
                Area city = area.getUpArea();
                Area province = city.getUpArea();
                if (BaseConstants.AREA_TYPE_PROVINCE.equals(city.getAreaType())) {
                    //直辖市
                    this.regionWithSHQ = city.getAreaName() + area.getAreaName();
                } else {
                    this.regionWithSHQ = province.getAreaName() + city.getAreaName() + area.getAreaName();
                }
            } else {
                this.regionWithSHQ = null;
            }
        } else {
            this.regionWithSHQ = null;
        }
        this.Loaded("regionWithSHQ");
        return this.regionWithSHQ;
    }

    public String getBankName2() {
        if (this.isLoaded("bankName2", this.bankName2)) {
            return this.bankName2;
        }
        this.bankName2 = "";
        if (!StrUtil.isNullOrEmpty(this.getBankName())) {
            AttrValue av = (AttrValue) super.getDefaultDao().getObject(AttrValue.class, NumericUtil.nullToIntegerZero(this.getBankName()));
            if (av != null) {
                this.bankName2 = av.getAttrValue();
            }
        }
        this.Loaded("bankName2");
        return this.bankName2;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }
}