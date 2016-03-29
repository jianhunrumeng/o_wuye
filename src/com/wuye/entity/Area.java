package com.wuye.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.services.AreaServiceManager;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer areaId;
    private String areaName;
    private String areaType;
    private Integer upAreaId;
    private Set addresses = new HashSet(0);
    private Area upArea;

    // Constructors

    /**
     * default constructor
     */
    public Area() {
    }

    /**
     * minimal constructor
     */
    public Area(Integer upAreaId) {
        this.upAreaId = upAreaId;
    }

    /**
     * full constructor
     */
    public Area(String areaName, String areaType, Integer upAreaId,
                String statusCd, Timestamp createDate, Timestamp statusDate,
                Timestamp updateDate, Set addresses) {
        this.areaName = areaName;
        this.areaType = areaType;
        this.upAreaId = upAreaId;
        this.statusCd = statusCd;
        this.createDate = createDate;
        this.statusDate = statusDate;
        this.updateDate = updateDate;
        this.addresses = addresses;
    }

    // Property accessors

    public Integer getAreaId() {
        return super.getId();
    }

    public void setAreaId(Integer areaId) {
        super.setId(areaId);
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return this.areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public Integer getUpAreaId() {
        return this.upAreaId;
    }

    public void setUpAreaId(Integer upAreaId) {
        this.upAreaId = upAreaId;
    }

    public Set getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set addresses) {
        this.addresses = addresses;
    }

    public Area getUpArea() {
        if (upAreaId != null && upAreaId.equals(0)) {
            this.upArea = null;
        } else {
            if (upArea == null) {
                this.upArea = (Area) getService().getObject(Area.class, this.upAreaId);
            }
        }

        return upArea;
    }

    public static AreaServiceManager getService() {
        return (AreaServiceManager) SpringUtil.getBean("areaServiceManager");
    }
}