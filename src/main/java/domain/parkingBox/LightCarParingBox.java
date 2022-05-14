package domain.parkingBox;

import domain.car.Car;
import domain.car.LightCar;

public class LightCarParingBox implements ParkingBox {
    private boolean carExist = false;

    @Override
    public void in(Car car) {
        if (!(car instanceof LightCar)) {
            throw new RuntimeException("경차 전용 칸입니다");
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
