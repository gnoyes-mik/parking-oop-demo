package domain.parkingBox;

import domain.car.Bus;
import domain.car.Car;
import domain.parkingLot.dto.CarParkingInfo;

public class BusParkingBox implements ParkingBox {
    private boolean carExist = false;
    private CarParkingInfo info;

    @Override
    public void in(CarParkingInfo carParkingInfo) {
        Car car = carParkingInfo.getCar();
        if (!(car instanceof Bus)) {
            throw new RuntimeException("버스 전용 칸입니다");
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
        return carExist;
    }

    @Override
    public CarParkingInfo getInfo() {
        return info;
    }
}
