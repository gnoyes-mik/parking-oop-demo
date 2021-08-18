package car;

public class Bus implements Car {
    private final String carNum;

    public Bus(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public String getCarNum() {
        return carNum;
    }
}
