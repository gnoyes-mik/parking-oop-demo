package domain.parkingLot.dto;

public class ParkingReceipt {
    private long cost;

    public ParkingReceipt(long cost) {
        this.cost = cost;
    }

    public long getCost() {
        return cost;
    }
}
