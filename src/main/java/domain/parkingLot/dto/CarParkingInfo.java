package domain.parkingLot.dto;

import domain.car.Car;
import utils.Location;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;


public class CarParkingInfo {

    private String parkingLotName;

    private Location location;

    private Car car;

    private LocalDateTime enterTime;

    private LocalDateTime leaveTime;

    public CarParkingInfo() {
    }

    public CarParkingInfo(String parkingLotName, Car car) {
        this.parkingLotName = parkingLotName;
        this.car = car;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(int floor, int no) {
        this.location = new Location(floor, no);
    }

    public int getFloor() {
        return location.getFloor();
    }

    public int getParkingBoxNo() {
        return location.getNo();
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getEnterTime() {
        return enterTime;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void recordEnterTime() {
        this.enterTime = now();
    }

    public void recordLeaveTime() {
        this.leaveTime = now();
    }

}
