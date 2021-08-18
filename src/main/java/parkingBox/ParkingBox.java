package parkingBox;

import car.Car;

public interface ParkingBox {
    void in(Car car);

    void out();

    boolean status();
}
