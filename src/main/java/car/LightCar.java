package car;

public class LightCar implements Car {
    private final String carNum;

    public LightCar(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public String getCarNum() {
        return carNum;
    }
}
