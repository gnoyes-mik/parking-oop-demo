package domain.parkingBox;

import domain.car.Car;
import domain.car.LightCar;
import domain.parkingLot.dto.CarParkingInfo;

public class LightCarParingBox implements ParkingBox {
    private boolean carExist = false;

    private CarParkingInfo info;

    @Override
    public void in(CarParkingInfo carParkingInfo) {
        Car car = carParkingInfo.getCar();
        if (!(car instanceof LightCar)) {
            throw new RuntimeException("경차 전용 칸입니다");
        }
        info = carParkingInfo;
        carExist = true;
    }

    @Override
    public void out() {
        carExist = false;
        info = null;
    }

    @Override
    public boolean status() {
        return this.carExist;
    }

    public CarParkingInfo getInfo() {
        return info;
    }
}
