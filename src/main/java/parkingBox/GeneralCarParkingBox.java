package parkingBox;

import car.Car;
import car.GeneralCar;
import car.LightCar;

public class GeneralCarParkingBox implements ParkingBox {

    private boolean carExist = false;

    @Override
    public void in(Car car) {
        if (!(car instanceof GeneralCar) && !(car instanceof LightCar)) {
            throw new RuntimeException("일반차량 전용 칸입니다");
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
