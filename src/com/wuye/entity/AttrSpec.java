package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.dao.AttrSpecDao;

import java.sql.Timestamp;
import java.util.*;

/**
 * AttrSpec entity. @author MyEclipse Persistence Tools
 */

public class AttrSpec extends BaseEntity implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer attrId;
    private String attrName;
    private String attrCode;
    private Integer classId;
    private String attrValueType;
    private String attrType;
    private Set attrValues = new HashSet(0);
    private String defalueValue;


    // Constructors

    /**
     * default constructor
     */
    public AttrSpec() {
    }

    /**
     * minimal constructor
     */
    public AttrSpec(String attrName, String attrCode) {
        this.attrName = attrName;
        this.attrCode = attrCode;
    }

    /**
     * full constructor
     */
    public AttrSpec(String attrName, String attrCode, Integer classId,
                    String attrValueType, String attrType, Timestamp createDate,
                    Timestamp updateDate, String statusCd, Timestamp statusDate,
                    Set attrValues) {
        this.attrName = attrName;
        this.attrCode = attrCode;
        this.classId = classId;
        this.attrValueType = attrValueType;
        this.attrType = attrType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.attrValues = attrValues;
    }

    // Property accessors

    public Integer getAttrId() {
        return super.getId();
    }

    public void setAttrId(Integer attrId) {
        super.setId(attrId);
    }

    public String getAttrName() {
        return this.attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrCode() {
        return this.attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public Integer getClassId() {
        return this.classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getAttrValueType() {
        return this.attrValueType;
    }

    public void setAttrValueType(String attrValueType) {
        this.attrValueType = attrValueType;
    }

    public String getAttrType() {
        return this.attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public Set getAttrValues() {
        return this.attrValues;
    }

    public void setAttrValues(Set attrValues) {
        this.attrValues = attrValues;
    }

    public List<AttrValue> getSysAttrValue() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attrId", this.getId());
        return getDao().getSysAttrValue(map);
    }

    public List<AttrValue> getAttrValue(Integer communityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attrId", this.getId());
        map.put("communityId", communityId);
        return getDao().getAttrValue(map);
    }

    public static AttrSpecDao getDao() {
        return (AttrSpecDao) SpringUtil.getBean("attrSpecDao");
    }

    public String getDefalueValue() {
        return this.defalueValue;
    }

    public void setDefalueValue(String defalueValue) {
        this.defalueValue = defalueValue;
    }
}