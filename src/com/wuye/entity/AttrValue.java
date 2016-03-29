package com.wuye.entity;

import com.wuye.constants.BaseConstants;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * AttrValue entity. @author MyEclipse Persistence Tools
 */

public class AttrValue extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer attrValueId;
    private Integer attrId;
    private AttrSpec attrSpec;
    private String attrValueName;
    private String attrValue;
    private Integer communityId;
    private Long parentValueId;
    private List<AttrValue> childrenAttrValueList;


    // Constructors

    /**
     * default constructor
     */
    public AttrValue() {
    }

    /**
     * minimal constructor
     */
    public AttrValue(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * full constructor
     */
    public AttrValue(AttrSpec attrSpec, String attrValueName, String attrValue,
                     Integer communityId, Timestamp createDate, Timestamp updateDate,
                     String statusCd, Timestamp statusDate) {
        this.attrSpec = attrSpec;
        this.attrValueName = attrValueName;
        this.attrValue = attrValue;
        this.communityId = communityId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getAttrValueId() {
        return super.getId();
    }

    public void setAttrValueId(Integer attrValueId) {
        super.setId(attrValueId);
    }

    public AttrSpec getAttrSpec() {
        return this.attrSpec;
    }

    public void setAttrSpec(AttrSpec attrSpec) {
        this.attrSpec = attrSpec;
    }

    public String getAttrValueName() {
        return this.attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public String getAttrValue() {
        return this.attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Integer getCommunityId() {
        return this.communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getAttrId() {
        return this.attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public List<AttrValue> getChildrenAttrValueList() {
        if (this.isLoaded("childrenAttrValueList", this.childrenAttrValueList)) {
            return this.childrenAttrValueList;
        }
        List params = new ArrayList();
        if (this.getAttrValueId() == 0L) {
            String hql = "from AttrValue a where a.attrId= ? and a.statusCd = ? and a.parentValueId is null ";
            params.add(this.getAttrId());
            params.add(BaseConstants.STATUS_VALID);
            this.childrenAttrValueList = this.getDefaultDao().findListByHQLAndParams(hql, params);
        } else {
            String hql = "from AttrValue a where a.attrId= ? and a.parentValueId = ? and a.statusCd = ?";
            params.add(this.getAttrId());
            params.add(this.getAttrValueId());
            params.add(BaseConstants.STATUS_VALID);
            this.childrenAttrValueList = this.getDefaultDao().findListByHQLAndParams(hql, params);
        }
        this.Loaded("childrenAttrValueList");
        return childrenAttrValueList;
    }

    public Long getParentValueId() {
        return this.parentValueId;
    }

    public void setParentValueId(Long parentValueId) {
        this.parentValueId = parentValueId;
    }
}