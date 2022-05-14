import domain.car.Bus;
import domain.car.Car;
import domain.car.LightCar;
import domain.parkingLot.ParkingLot;
import domain.parkingLot.StandardParkingLot;

public class Main {

    public static void main(String[] args) {
        ParkingLot parkingLot = new StandardParkingLot(5, 20);

        Car damas = new LightCar("62머 1234");
        parkingLot.park(1, 1, damas);

        Car schoolBus = new Bus("31가 9999");
        // parkingLot.park(1, 1, schoolBus); // 차량이 이미 존재합니다
        // parkingLot.park(1, 2, schoolBus); // 일반차량 전용 칸입니다
        parkingLot.park(2, 20, schoolBus);

        parkingLot.show();

        parkingLot.moveOut(1, 1, damas);
        parkingLot.moveOut(2, 20, schoolBus);

        parkingLot.show();
    }
}
