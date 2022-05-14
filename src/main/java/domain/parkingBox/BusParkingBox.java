package domain.parkingBox;

import domain.car.Bus;
import domain.car.Car;

public class BusParkingBox implements ParkingBox {
    private boolean carExist = false;

    @Override
    public void in(Car car) {
        if (!(car instanceof Bus)) {
            throw new RuntimeException("버스 전용 칸입니다");
        }
        carExist = true;
    }

    @Override
    public void out() {
        carExist = false;
    }

    @Override
    public boolean status() {
        return this.carExist;
    }
}
