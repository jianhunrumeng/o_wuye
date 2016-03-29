package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.services.AttrValueService;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AcctItemType entity. @author MyEclipse Persistence Tools
 */

public class AcctItemType extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer acctItemTypeId;
    private String acctTypeName;
    private String acctType;
    private Integer parentAcctTypeId;
    private String classId;
    private Integer objId;
    private Set acctItemRels = new HashSet(0);
    private Set extraordinaryFees = new HashSet(0);
    private AcctItemType parentAcctItemType = null;

    // Constructors

    public void setParentAcctItemType(AcctItemType parentAcctItemType) {
        this.parentAcctItemType = parentAcctItemType;
    }

    /**
     * default constructor
     */
    public AcctItemType() {
    }

    /**
     * full constructor
     */
    public AcctItemType(String acctTypeName, String acctType,
                        Integer parentAcctTypeId, String classId, Integer objId,
                        Timestamp createDate, Timestamp updateDate, String statusCd,
                        Timestamp statusDate, Set acctItemRels, Set extraordinaryFees) {
        this.acctTypeName = acctTypeName;
        this.acctType = acctType;
        this.parentAcctTypeId = parentAcctTypeId;
        this.classId = classId;
        this.objId = objId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.acctItemRels = acctItemRels;
        this.extraordinaryFees = extraordinaryFees;
    }

    // Property accessors

    public Integer getAcctItemTypeId() {
        return super.getId();
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        super.setId(acctItemTypeId);
    }

    public String getAcctTypeName() {
        return this.acctTypeName;
    }

    public void setAcctTypeName(String acctTypeName) {
        this.acctTypeName = acctTypeName;
    }

    public String getAcctType() {
        return this.acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public Integer getParentAcctTypeId() {
        return this.parentAcctTypeId;
    }

    public void setParentAcctTypeId(Integer parentAcctTypeId) {
        this.parentAcctTypeId = parentAcctTypeId;
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

    public Set getAcctItemRels() {
        return this.acctItemRels;
    }

    public void setAcctItemRels(Set acctItemRels) {
        this.acctItemRels = acctItemRels;
    }

    public Set getExtraordinaryFees() {
        return this.extraordinaryFees;
    }

    public void setExtraordinaryFees(Set extraordinaryFees) {
        this.extraordinaryFees = extraordinaryFees;
    }

    /**
     * 获取父类
     *
     * @return
     */
    public AcctItemType getParentAcctItemType() {
        if (StrUtil.isNullOrEmpty(this.parentAcctItemType)
                && !StrUtil.isNullOrEmpty(this.parentAcctTypeId)) {
            this.parentAcctItemType = (AcctItemType) this.getDefaultDao().getObject(AcctItemType.class, this.parentAcctTypeId);
        }
        return this.parentAcctItemType;
    }

    public String getParentAcctItemTypeName() {
        String acctItemTypeName = "";
        AcctItemType acctItemType = this.getParentAcctItemType();
        if (acctItemType != null) {
            acctItemTypeName = acctItemType.getAcctTypeName();
        }
        return acctItemTypeName;
    }

    /**
     * 绑定一个临时父类的id，用于界面绑定使用，和细类不冲突
     *
     * @return
     */
    public Integer getTempAcctItemTypeId() {
        return this.getAcctItemTypeId();
    }

    /**
     * 获取单位信息
     *
     * @return
     */
    public String getUnit() {
        String unit = "";
        if (!StrUtil.isNullOrEmpty(this.acctType)) {
            AttrValue attrValue = getAttrValueService().getAttrValue(Integer.parseInt(BaseConstants.CLASS_ACCT_ITEM_TYPE), "unit", null, this.acctType, true);
            if (attrValue != null) {
                unit = attrValue.getAttrValueName();
            }
        }
        return unit;
    }

    /**
     * 获取attrValue
     *
     * @return
     */
    public static AttrValueService getAttrValueService() {
        return (AttrValueService) SpringUtil.getBean("attrValueService");
    }
}