package com.wuye.entity;

import com.wuye.common.util.string.StrUtil;
import com.wuye.constants.BaseConstants;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MeterSpec entity. @author MyEclipse Persistence Tools
 */

public class MeterSpec extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer meterSpecId;
    private String meterType;
    private Integer parentMeterId;
    private String meterName;
    private String unit;
    private Float price;
    private String classId;
    private Integer objId;
    private Set meterInsts = new HashSet(0);
    private MeterSpec parentMeterSpec;
    private BaseEntity obj;

    // Constructors

    /**
     * default constructor
     */
    public MeterSpec() {
    }

    /**
     * minimal constructor
     */
    public MeterSpec(String meterType, String meterName) {
        this.meterType = meterType;
        this.meterName = meterName;
    }

    /**
     * full constructor
     */
    public MeterSpec(String meterType, Integer parentMeterId, String meterName,
                     String unit, Float price, String classId, Integer objId,
                     Timestamp createDate, Timestamp updateDate, String statusCd,
                     Timestamp statusDate, Set meterInsts) {
        this.meterType = meterType;
        this.parentMeterId = parentMeterId;
        this.meterName = meterName;
        this.unit = unit;
        this.price = price;
        this.classId = classId;
        this.objId = objId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
        this.meterInsts = meterInsts;
    }

    // Property accessors

    public Integer getMeterSpecId() {
        return super.getId();
    }

    public void setMeterSpecId(Integer meterSpecId) {
        super.setId(meterSpecId);
    }

    public String getMeterType() {
        return this.meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public Integer getParentMeterId() {
        return this.parentMeterId;
    }

    public void setParentMeterId(Integer parentMeterId) {
        this.parentMeterId = parentMeterId;
    }

    public String getMeterName() {
        return this.meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public void setObjId(Integer attribute55) {
        this.objId = attribute55;
    }

    public Set getMeterInsts() {
        return this.meterInsts;
    }

    public void setMeterInsts(Set meterInsts) {
        this.meterInsts = meterInsts;
    }

    /**
     * 获取父类
     */
    public MeterSpec getParentMeterSpec() {
        if (this.isLoaded("parentMeterSpec", this.parentMeterSpec)) {
            return this.parentMeterSpec;
        }
        if (StrUtil.isNullOrEmpty(this.getParentMeterId())) {
            return null;
        }
        this.parentMeterSpec = (MeterSpec) this.getDefaultDao().getObject(MeterSpec.class, this.parentMeterId);
        return this.parentMeterSpec;
    }

    /**
     * 获取父类名称
     */
    public String getParentMeterName() {
        String parentMeterName = "";
        if (!StrUtil.isNullOrEmpty(this.getParentMeterSpec())) {
            parentMeterName = this.getParentMeterSpec().getMeterName();
        }
        return parentMeterName;
    }

    public void setObj(BaseEntity obj) {
        this.obj = obj;
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
}