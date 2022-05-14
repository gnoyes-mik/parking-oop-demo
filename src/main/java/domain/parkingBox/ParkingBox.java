package domain.parkingBox;

import domain.car.Car;

public interface ParkingBox {
    void in(Car car);

    void out();

    boolean status();
}
