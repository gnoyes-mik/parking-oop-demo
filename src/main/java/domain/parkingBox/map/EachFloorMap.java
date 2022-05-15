package domain.parkingBox.map;

import domain.parkingBox.BusParkingBox;
import domain.parkingBox.GeneralCarParkingBox;
import domain.parkingBox.LightCarParingBox;
import domain.parkingBox.ParkingBox;

public class EachFloorMap implements MapStrategy {

    public EachFloorMap() {
    }

    public ParkingBox[][] createMap(int floor, int no) {
        return new ParkingBox[floor + 1][no + 1];
    }
}
