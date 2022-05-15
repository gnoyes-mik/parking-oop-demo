package domain.parkingBox.map;

import domain.parkingBox.BusParkingBox;
import domain.parkingBox.GeneralCarParkingBox;
import domain.parkingBox.LightCarParingBox;
import domain.parkingBox.ParkingBox;

public class StandardMap implements MapStrategy {

    public StandardMap() {
    }

    public ParkingBox[][] createMap(int floor, int no) {
        ParkingBox[][] parkingLot = new ParkingBox[floor + 1][no + 1];
        for (int f = 1; f <= floor; f++) {
            for (int n = 1; n <= no; n++) {
                if (n == no) {
                    parkingLot[f][n] = new BusParkingBox();
                    continue;
                }
                if (n % 2 == 0) {
                    parkingLot[f][n] = new GeneralCarParkingBox();
                    continue;
                }
                parkingLot[f][n] = new LightCarParingBox();
            }
        }
        return parkingLot;
    }
}
