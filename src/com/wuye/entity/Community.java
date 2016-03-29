package com.wuye.entity;

import com.wuye.common.exception.RtManagerException;
import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.CommunityDao;
import com.wuye.services.CommunityServiceManager;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Community entity. @author MyEclipse Persistence Tools
 */

public class Community extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer communityId;
    private Integer companyId;
    private Organization organization;
    private PropertyCompany propertyCompany;
    private PartyInfo communityInfo;
    private String communityName;
    private String simpleName;
    private Float communityArea;
    private Float buildingArea;
    private Float afforestArea;
    private String bankAcctName;
    /**
     * 银行属性值ID
     */
    private String bankName;
    /**
     * 银行名称
     */
    private String bankName2;
    private String bankAcctNbr;
    private Set buildings = new HashSet(0);
    private Set monthlyBalances = new HashSet(0);
    private PartyInfo partyInfo;
    /**
     * 封装小区地址的省市区名称，如海南省海口市龙华区
     */
    private String regionWithSHQ;
    // Constructors

    /**
     * default constructor
     */
    public Community() {
    }

    /**
     * minimal constructor
     */
    public Community(Organization organization,
                     PropertyCompany propertyCompany, PartyInfo communityInfo,
                     String communityName) {
        this.organization = organization;
        this.propertyCompany = propertyCompany;
        this.communityInfo = communityInfo;
        this.communityName = communityName;
    }

    /**
     * full constructor
     */
    public Community(Organization organization,
                     PropertyCompany propertyCompany, PartyInfo communityInfo,
                     String communityName, String simpleName, Float communityArea,
                     Float buildingArea, Float afforestArea, String bankAcctName,
                     String bankName, String bankAcctNbr, String statusCd,
                     Timestamp createDate, Timestamp statusDate, Timestamp updateDate,
                     Set buildings, Set monthlyBalances) {
        this.organization = organization;
        this.propertyCompany = propertyCompany;
        this.communityInfo = communityInfo;
        this.communityName = communityName;
        this.simpleName = simpleName;
        this.communityArea = communityArea;
        this.buildingArea = buildingArea;
        this.afforestArea = afforestArea;
        this.bankAcctName = bankAcctName;
        this.bankName = bankName;
        this.bankAcctNbr = bankAcctNbr;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
        this.buildings = buildings;
        this.monthlyBalances = monthlyBalances;
    }

    // Property accessors

    public Integer getCommunityId() {
        return super.getId();
    }

    public void setCommunityId(Integer communityId) {
        super.setId(communityId);
    }

    public Organization getOrganization() {
        return this.organization;
        /*if (this.isLoaded("organization", this.organization)){
			return this.organization;
		}*/
        //this.organization = getDao().getObject(Organization.class, )
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public PropertyCompany getPropertyCompany() {
        return this.propertyCompany;
    }

    public void setPropertyCompany(PropertyCompany propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public PartyInfo getCommunityInfo() {
        return this.communityInfo;
    }

    public void setCommunityInfo(PartyInfo communityInfo) {
        this.communityInfo = communityInfo;
    }

    public String getCommunityName() {
        return this.communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public Float getCommunityArea() {
        return this.communityArea;
    }

    public void setCommunityArea(Float communityArea) {
        this.communityArea = communityArea;
    }

    public Float getBuildingArea() {
        return this.buildingArea;
    }

    public void setBuildingArea(Float buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Float getAfforestArea() {
        return this.afforestArea;
    }

    public void setAfforestArea(Float afforestArea) {
        this.afforestArea = afforestArea;
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

    public Set getBuildings() {
        return this.buildings;
    }

    public void setBuildings(Set buildings) {
        this.buildings = buildings;
    }

    public Set getMonthlyBalances() {
        return this.monthlyBalances;
    }

    public void setMonthlyBalances(Set monthlyBalances) {
        this.monthlyBalances = monthlyBalances;
    }

    public void save() {
        if (this.getOrganization() != null) {
            this.getOrganization().save();
        }

        if (this.getCommunityInfo() != null) {
            this.getCommunityInfo().save();
        }
        if (this.getPropertyCompany() != null) {
            this.getPropertyCompany().save();
        }
        super.save();
        if (this.getPartyInfo() != null) {
            this.getPartyInfo().setClassId(BaseConstants.CLASS_COMMUNITY);
            this.getPartyInfo().setObjId(this.getCommunityId());
            this.getPartyInfo().save();
        }
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
        if (partyInfo.getPartyInfoId() == null) {
            partyInfo.setClassId(BaseConstants.CLASS_COMMUNITY);
        }
    }

    public PartyInfo getPartyInfo() {
        if (this.isLoaded("partyInfo", this.partyInfo)) {
            return this.partyInfo;
        }
        this.partyInfo = Community.getService().getPartyInfo(this.getId());
        this.Loaded("partyInfo");
        return partyInfo;
    }

    public static CommunityDao getDao() {
        return (CommunityDao) SpringUtil.getBean("communityDao");
    }

    public static CommunityServiceManager getService() {
        return (CommunityServiceManager) SpringUtil.getBean("communityServiceManager");
    }

    public void remove() {
        //判断是否有楼栋
        String hql = "SELECT 1 FROM DUAL WHERE EXISTS ( SELECT 1 FROM building c WHERE c.community_id = ? ) ";
        int count = super.getDefaultDao().jdbcQueryForInt(hql, this.getId());
        if (count > 0) {
            throw new RtManagerException("请先删除小区下所有楼栋/单元数据再删除小区");
        }
        super.remove();
        if (this.getCommunityInfo() != null) {
//			this.getCommunityInfo().getCommunities().remove(this);
            this.getCommunityInfo().remove();
        }

        if (this.getOrganization() != null) {
//			this.getOrganization().getCommunities().remove(this);
            this.getOrganization().remove();
        }

        if (this.getPartyInfo() != null) {
//			this.getPartyInfo().getCommunities().remove(this);
            this.getPartyInfo().remove();
        }
		/*this.setPropertyCompany(null);
		this.setCommunityInfo(null);
		this.setOrganization(null);
		this.setPropertyCompany(null);*/

    }

    public String getRegionWithSHQ() {
        if (this.isLoaded("regionWithSHQ", this.regionWithSHQ)) {
            return this.regionWithSHQ;
        }
        if (this.getCommunityInfo() != null) {
            Address add = this.getCommunityInfo().getAddress();
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

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}