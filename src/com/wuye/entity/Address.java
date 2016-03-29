package com.wuye.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address extends BaseEntity implements java.io.Serializable {

    // Fields
    private Integer addressId;
    private Area area;
    private String detailAddress;
    private Set partyInfos = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Address() {
    }

    /**
     * minimal constructor
     */
    public Address(Area area) {
        this.area = area;
    }

    /**
     * full constructor
     */
    public Address(Area area, String detailAddress, Set partyInfos) {
        this.area = area;
        this.detailAddress = detailAddress;
        this.partyInfos = partyInfos;
    }

    // Property accessors

    public Integer getAddressId() {
        return super.getId();
    }

    public void setAddressId(Integer addressId) {
        super.setId(addressId);
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Set getPartyInfos() {
        return this.partyInfos;
    }

    public void setPartyInfos(Set partyInfos) {
        this.partyInfos = partyInfos;
    }

}