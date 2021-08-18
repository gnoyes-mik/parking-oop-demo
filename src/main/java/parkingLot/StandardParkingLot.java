package parkingLot;

import car.Car;
import parkingBox.BusParkingBox;
import parkingBox.GeneralCarParkingBox;
import parkingBox.ParkingBox;

import java.util.logging.Logger;

public class StandardParkingLot implements ParkingLot {

    private ParkingBox[][] parkingLot;
    private final int MAX_FLOOR;
    private final int MAX_NO;
    private final int MIN_FLOOR = 1;
    private final int MIN_NO = 1;

    public StandardParkingLot(int floor, int no) {
        this.parkingLot = generateParkingBoxes(floor, no);
        this.MAX_FLOOR = floor;
        this.MAX_NO = no;
    }

    private ParkingBox[][] generateParkingBoxes(int floor, int no) {
        parkingLot = new ParkingBox[floor + 1][no + 1];
        for (int f = 1; f <= floor; f++) {
            for (int n = 1; n <= no; n++) {
                if (n == no) {
                    parkingLot[f][n] = new BusParkingBox();
                    continue;
                }
                parkingLot[f][n] = new GeneralCarParkingBox();
            }
        }
        return parkingLot;
    }

    @Override
    public void park(int floor, int no, Car car) {
        validateParam(floor, no);

        if (parkingLot[floor][no].status()) {
            throw new RuntimeException("차량이 이미 존재합니다");
        }

        parkingLot[floor][no].in(car);
        System.out.println("차량 번호 [" + car.getCarNum() + "]가 주차되었습니다");
    }

    @Override
    public void moveOut(int floor, int no, Car car) {
        parkingLot[floor][no].out();
        System.out.println("차량 번호 [" + car.getCarNum() + "]가 출차되었습니다");
    }

    private void validateParam(int floor, int no) {
        if (floor < MIN_FLOOR || no < MIN_NO) {
            throw new RuntimeException("최솟값 작습니다");
        }

        if (floor > MAX_FLOOR || no > MAX_NO) {
            throw new RuntimeException("최댓값 큽니다");
        }
    }

    @Override
    public void show() {
        for (int f = 1; f <= MAX_FLOOR; f++) {
            System.out.print(f + "층 ");
            for (int n = 1; n <= MAX_NO; n++) {
                System.out.print(parkingLot[f][n].status() ? "■" : "□");
            }
            System.out.println();
        }
    }
}
