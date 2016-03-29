package com.wuye.entity;

import java.sql.Timestamp;

/**
 * VehicleRel entity. @author MyEclipse Persistence Tools
 */

public class VehicleRel extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer vehicleRelId;
    private Vehicle vehicle;
    private Room room;
    private ParkingType parkingType;
    private Parking parking;
    private String plateNbr;

    // Constructors

    /**
     * default constructor
     */
    public VehicleRel() {
    }

    /**
     * minimal constructor
     */
    public VehicleRel(Vehicle vehicle, Room room, ParkingType parkingType) {
        this.vehicle = vehicle;
        this.room = room;
        this.parkingType = parkingType;
    }

    /**
     * full constructor
     */
    public VehicleRel(Vehicle vehicle, Room room, ParkingType parkingType,
                      Parking parking, Timestamp createDate, Timestamp updateDate,
                      String statusCd, Timestamp statusDate) {
        this.vehicle = vehicle;
        this.room = room;
        this.parkingType = parkingType;
        this.parking = parking;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.statusCd = statusCd;
        this.statusDate = statusDate;
    }

    // Property accessors

    public Integer getVehicleRelId() {
        return super.getId();
    }

    public void setVehicleRelId(Integer vehicleRelId) {
        super.setId(vehicleRelId);
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ParkingType getParkingType() {
        return this.parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public Parking getParking() {
        return this.parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public String getPlateNbr() {
        return this.plateNbr;
    }

    public void setPlateNbr(String plateNbr) {
        this.plateNbr = plateNbr;
    }
}