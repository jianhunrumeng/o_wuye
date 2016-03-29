package com.wuye.entity;

import java.sql.Timestamp;

/**
 * RoomPartyRel entity. @author MyEclipse Persistence Tools
 */

public class RoomPartyRel extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer roomPartyRelId;
    private PartyInfo partyInfo;
    private Room room;
    private String relType;
    private String remark;


    // Constructors

    /**
     * default constructor
     */
    public RoomPartyRel() {
    }

    /**
     * minimal constructor
     */
    public RoomPartyRel(PartyInfo partyInfo, Room room) {
        this.partyInfo = partyInfo;
        this.room = room;
    }

    /**
     * full constructor
     */
    public RoomPartyRel(PartyInfo partyInfo, Room room, String relType,
                        String remark, Timestamp createDate, Timestamp updateDate,
                        String statusCd, Timestamp statusDate) {
        this.partyInfo = partyInfo;
        this.room = room;
        this.relType = relType;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getRoomPartyRelId() {
        return super.getId();
    }

    public void setRoomPartyRelId(Integer roomPartyRelId) {
        super.setId(roomPartyRelId);
    }

    public PartyInfo getPartyInfo() {
        return this.partyInfo;
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getRelType() {
        return this.relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void save() {
        if (this.getPartyInfo() != null) {
            this.getPartyInfo().save();
        }
        super.save();
    }
}