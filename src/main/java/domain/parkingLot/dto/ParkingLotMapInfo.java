package domain.parkingLot.dto;

import domain.parkingBox.map.MapStrategy;


public class ParkingLotMapInfo {
    private int floor;
    private int no;
    private MapStrategy strategy;

    public ParkingLotMapInfo() {
    }

    public ParkingLotMapInfo(int floor, int no, MapStrategy strategy) {
        this.floor = floor;
        this.no = no;
        this.strategy = strategy;
    }

    public int getFloor() {
        return floor;
    }

    public int getNo() {
        return no;
    }

    public MapStrategy getStrategy() {
        return strategy;
    }
}
