package domain.parkingBox.map;

import domain.parkingBox.ParkingBox;

public interface MapStrategy {
    ParkingBox[][] createMap(int floor, int no);
}
