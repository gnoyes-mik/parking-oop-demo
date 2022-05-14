package domain.parkingLot.dto;

import domain.car.Car;
import utils.Location;

import java.time.LocalDateTime;

public class CarParkingInfoBuilder {
    public static CarParkingInfo of(String parkingLotName, Location location, Car car, LocalDateTime enterTime, LocalDateTime leaveTime) {
        return new CarParkingInfo(parkingLotName, location, car, enterTime, leaveTime);
    }
}