package domain.parkingBox;

import domain.parkingLot.dto.CarParkingInfo;

public interface ParkingBox {
    void in(CarParkingInfo carParkingInfo);

    void out();

    boolean status();

    CarParkingInfo getInfo();
}
