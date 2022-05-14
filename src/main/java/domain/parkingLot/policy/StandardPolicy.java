package domain.parkingLot.policy;

public class StandardPolicy implements ParkingCostPolicy {

    private long costPer10minute;

    public StandardPolicy(long costPer10minute) {
        if(costPer10minute < 0) {
            throw new RuntimeException("0보다 작을 수 없습니다");
        }
        this.costPer10minute = costPer10minute;
    }

    @Override
    public long getCostPer10min() {
        return costPer10minute;
    }
}
