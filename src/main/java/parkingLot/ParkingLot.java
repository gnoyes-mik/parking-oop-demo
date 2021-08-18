package parkingLot;

import car.Car;

public interface ParkingLot {
    void park(int floor, int no, Car car);

    void moveOut(int floor, int no, Car car);

    void show();
}
