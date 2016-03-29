package com.wuye.entity;

import com.wuye.constants.BaseConstants;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Organization entity. @author MyEclipse Persistence Tools
 */

public class Organization extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer orgId;
    private String orgName;
    private String orgType;
    private Integer upOrgId;
    private Set communities = new HashSet(0);
    private Set userOrgRels = new HashSet(0);
    private Set propertyCompanies = new HashSet(0);
    /**
     * 组织所属物业公司
     */
    private PropertyCompany ownerCompany;

    /**
     * 组织所属小区
     */
    private Community ownerCommunity;
    /**
     * 上级组织
     */
    private Organization upOrganization;
    /**
     * 直接下级组织
     */
    private List<Organization> child;
    /**
     * 所有下级组织，包括下级组织的下级组织
     */
    private List<Organization> allChild;
    // Constructors

    /**
     * default constructor
     */
    public Organization() {
    }

    /**
     * minimal constructor
     */
    public Organization(String orgName, String orgType, Integer upOrgId) {
        this.orgName = orgName;
        this.orgType = orgType;
        this.upOrgId = upOrgId;
    }

    /**
     * full constructor
     */
    public Organization(String orgName, String orgType, Integer upOrgId,
                        String statusCd, Timestamp createDate, Timestamp statusDate,
                        Timestamp updateDate, Set communities, Set userOrgRels,
                        Set propertyCompanies) {
        this.orgName = orgName;
        this.orgType = orgType;
        this.upOrgId = upOrgId;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
        this.communities = communities;
        this.userOrgRels = userOrgRels;
        this.propertyCompanies = propertyCompanies;
    }

    // Property accessors

    public Integer getOrgId() {
        return super.getId();
    }

    public void setOrgId(Integer orgId) {
        super.setId(orgId);
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return this.orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getUpOrgId() {
        return this.upOrgId;
    }

    public void setUpOrgId(Integer upOrgId) {
        this.upOrgId = upOrgId;
    }

    public Set getCommunities() {
        return this.communities;
    }

    public void setCommunities(Set communities) {
        this.communities = communities;
    }

    public Set getUserOrgRels() {
        return this.userOrgRels;
    }

    public void setUserOrgRels(Set userOrgRels) {
        this.userOrgRels = userOrgRels;
    }

    public Set getPropertyCompanies() {
        return this.propertyCompanies;
    }

    public void setPropertyCompanies(Set propertyCompanies) {
        this.propertyCompanies = propertyCompanies;
    }

    public void save() {
        super.save();
    }

    public void remove() {

        super.remove();
    }

    public PropertyCompany getOwnerCompany() {
        if (this.isLoaded("ownerCompany", this.ownerCompany)) {
            return this.ownerCompany;
        }
        if (this.getUpOrgId() == null || this.getUpOrgId().equals(0)) {
            //组织是物业公司
            if (this.getPropertyCompanies() != null && this.getPropertyCompanies().size() > 0) {
                List<PropertyCompany> companies = new ArrayList<PropertyCompany>(this.getPropertyCompanies());
                this.ownerCompany = companies.get(0);

            } else {
                this.ownerCompany = null;
            }
            this.Loaded("ownerCompany");
            return this.ownerCompany;
        } else {
            //其它组织需要获取顶级组织
            Organization tmpUpOrg = this;
            while (this.getUpOrganization() != null) {
                tmpUpOrg = tmpUpOrg.getUpOrganization();
            }
            this.ownerCompany = tmpUpOrg.getOwnerCompany();
            this.Loaded("ownerCompany");
            return this.ownerCompany;
        }
    }

    public Organization getUpOrganization() {
        if (this.isLoaded("upOrganization", this.upOrganization)) {
            return this.upOrganization;
        }
        if (this.getUpOrgId() == null || this.getUpOrgId().equals(0)) {
            //组织是物业公司
            this.upOrganization = null;
            this.Loaded("upOrganization");
            return this.upOrganization;
        } else {
            //其它组织需要获取顶级组织
            this.upOrganization = (Organization) super.getDefaultDao().getObject(Organization.class, this.getUpOrgId());
            this.Loaded("upOrganization");
            return this.upOrganization;
        }
    }

    public List<Organization> getChild() {
        if (this.isLoaded("child", this.child)) {
            return this.child;
        }
        String hql = "from organization a where a.upOrgId = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(this.getId());
        this.child = super.getDefaultDao().findListByHQLAndParams(hql, params);
        this.Loaded("child");
        return this.child;
    }

    public List<Organization> getAllChild() {

        if (this.isLoaded("allChild", this.allChild)) {
            return this.allChild;
        }/*
        List<Organization> childs = new ArrayList<Organization>();
		if (this.getAllChild() == null ){
			this.allChild = null;
			this.Loaded("allChild");
			return this.allChild;
		}
		while(this.getAllChild() != null){
			childs.addAll(this.getAllChild());
		}*/
        this.allChild = this.getChild();
        this.Loaded("allChild");
        return this.allChild;
    }

    /**
     * 取组合关联的小区
     *
     * @return
     */
    public Community getOwnerCommunity() {
        if (this.isLoaded("ownerCommunity", this.ownerCommunity)) {
            return this.ownerCommunity;
        }

        if (BaseConstants.ORG_TYPE_COMPANY.equals(this.getOrgType())) {
            //组织是物业公司
            this.ownerCommunity = null;
            this.Loaded("ownerCommunity");
            return this.ownerCommunity;
        } else if (BaseConstants.ORG_TYPE_COMMUNITY.equals(this.getOrgType())) {
            //组织类型为小区
            String hql = "from Community c where c.organization.orgId = ? ";
            List<Object> params = new ArrayList<Object>();
            params.add(this.getId());
            List<Community> communities = super.getDefaultDao().findListByHQLAndParams(hql, params, BaseConstants.QUERY_ROW_MAX);
            if (communities != null && communities.size() > 0) {
                this.ownerCommunity = communities.get(0);
            }
            this.Loaded("ownerCommunity");
            return this.ownerCommunity;
        } else {
            //其它类型
            Organization tmpUpOrg = this;
            while (this.getUpOrganization() != null) {
                tmpUpOrg = tmpUpOrg.getUpOrganization();
            }
            this.ownerCommunity = tmpUpOrg.getOwnerCommunity();
            this.Loaded("ownerCommunity");
            return this.ownerCommunity;
        }
    }
}