package car;

public class GeneralCar implements Car {
    private final String carNum;

    public GeneralCar(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public String getCarNum() {
        return carNum;
    }
}
