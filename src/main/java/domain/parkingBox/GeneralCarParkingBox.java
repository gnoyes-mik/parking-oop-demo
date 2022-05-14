package domain.parkingBox;

import domain.car.Car;
import domain.car.GeneralCar;
import domain.car.LightCar;
import domain.parkingLot.dto.CarParkingInfo;

public class GeneralCarParkingBox implements ParkingBox {

    private boolean carExist = false;

    private CarParkingInfo info;

    @Override
    public void in(CarParkingInfo carParkingInfo) {
        Car car = carParkingInfo.getCar();
        if (!(car instanceof GeneralCar) && !(car instanceof LightCar)) {
            throw new RuntimeException("일반차량 전용 칸입니다");
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

    public CarParkingInfo getInfo() {
        return info;
    }
}
