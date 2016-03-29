package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AttrValueDao;
import com.wuye.services.AttrValueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * AcctItemRel entity. @author MyEclipse Persistence Tools
 */

public class AcctItemRel extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer acctItemRelId;
    private AcctItemType acctItemType;
    private String classId;
    private Integer objId;
    private Float price;
    private Integer floor;
    private String caculateMethod;
    private Integer acctItemTypeId;
    private BaseEntity obj;

    @Autowired
    private AttrValueService attrValueService;
    @Autowired
    private AttrValueDao attrValueDao;


    // Constructors

    public String getCaculateMethod() {
        return caculateMethod;
    }

    public void setCaculateMethod(String caculateMethod) {
        this.caculateMethod = caculateMethod;
    }

    /**
     * default constructor
     */
    public AcctItemRel() {
    }

    /**
     * full constructor
     */
    public AcctItemRel(AcctItemType acctItemType, String classId,
                       Integer objId, Float price, Integer floor, Timestamp createDate,
                       Timestamp updateDate, String statusCd, Timestamp statusDate) {
        this.acctItemType = acctItemType;
        this.classId = classId;
        this.objId = objId;
        this.price = price;
        this.floor = floor;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getAcctItemRelId() {
        return super.getId();
    }

    public void setAcctItemRelId(Integer acctItemRelId) {
        super.setId(acctItemRelId);
    }

    public AcctItemType getAcctItemType() {
        if (this.isLoaded("acctItemType", this.acctItemType)) {
            return this.acctItemType;
        }
        if (StrUtil.isNullOrEmpty(this.acctItemTypeId)) {
            return null;
        }
        this.acctItemType = (AcctItemType) this.getDefaultDao().getObject(AcctItemType.class, this.acctItemTypeId);
        this.Loaded("acctItemType");
        return this.acctItemType;
    }

    public void setAcctItemType(AcctItemType acctItemType) {
        this.acctItemType = acctItemType;
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

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getFloor() {
        return this.floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public BaseEntity getObj() {
        if (this.isLoaded("obj", this.obj)) {
            return this.obj;
        }
        this.obj = this.getDefaultDao().getObj(this.classId, this.objId);
        this.Loaded("obj");
        return this.obj;
    }

    /**
     * 通过class_id和对象id查询相应实体名称
     *
     * @param classId
     * @param objId
     * @return
     */
    public String getObjName() {
        if (BaseConstants.CLASS_COMPANY.equals(this.classId)) {
            PropertyCompany propertyCompany = (PropertyCompany) this.getObj();
            if (propertyCompany != null) {
                return propertyCompany.getCompanyName();
            }
        } else if (BaseConstants.CLASS_COMMUNITY.equals(this.classId)) {
            Community community = (Community) this.getObj();
            if (community != null) {
                return community.getCommunityName();
            }
        } else if (BaseConstants.CLASS_BUILDING.equals(this.classId)) {
            Building building = (Building) this.getObj();
            if (building != null) {
                return building.getBuildingName();
            }
        } else if (BaseConstants.CLASS_ROOM.equals(this.classId)) {
            Room room = (Room) this.getObj();
            if (room != null) {
                return room.getRoomNbr();
            }
        }
        return "";
    }

    public void setObj(BaseEntity obj) {
        this.obj = obj;
    }

    /**
     * 获取物业费用细类名称
     */
    public String getAcctItemTypeName() {
        String acctItemTypeName = "";
        AcctItemType acctItemType = this.getAcctItemType();
        if (acctItemType != null) {
            acctItemTypeName = acctItemType.getAcctTypeName();
        }
        return acctItemTypeName;
    }

    /**
     * 获取费用大类名称
     */
    public String getParentAcctItemTypeName() {
        String acctItemTypeName = "";
        AcctItemType acctItemType = this.getAcctItemType();
        if (acctItemType != null) {
            acctItemTypeName = acctItemType.getParentAcctItemTypeName();
        }
        return acctItemTypeName;
    }

    /**
     * 获取费用大类名称
     */
    public Integer getParentAcctTypeId() {
        Integer acctItemTypeId = null;
        AcctItemType acctItemType = this.getAcctItemType();
        if (acctItemType != null) {
            acctItemTypeId = acctItemType.getParentAcctTypeId();
        }
        return acctItemTypeId;
    }

    /**
     * 获取收费方式
     *
     * @return
     */
    public String getCaculateMethodName() {
        String caculateMethodName = "";
        if (!StrUtil.isNullOrEmpty(this.caculateMethod)) {
            AttrValue attrValue = getAttrValueService().getAttrValue(Integer.parseInt(BaseConstants.CLASS_ACCT_ITEM_TYPE), "caculate_method", null, this.caculateMethod, true);
            if (attrValue != null) {
                caculateMethodName = attrValue.getAttrValueName();
            }
        }
        return caculateMethodName;
    }

    /**
     * 获取attrValu
     *
     * @return
     */
    public static AttrValueService getAttrValueService() {
        return (AttrValueService) SpringUtil.getBean("attrValueService");
    }

    public static AttrValueDao getAttrValueDao() {
        return (AttrValueDao) SpringUtil.getBean("attrValueDao");
    }

    public Integer getAcctItemTypeId() {
        return this.acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }
}