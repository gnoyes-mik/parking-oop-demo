package domain.parkingLot;

import domain.car.Car;

public interface ParkingLot {
    void park(int floor, int no, Car car);

    void moveOut(int floor, int no, Car car);

    void show();
}
