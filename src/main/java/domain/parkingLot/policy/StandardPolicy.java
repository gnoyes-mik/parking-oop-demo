package domain.parkingLot.policy;

import java.time.Duration;

public class StandardPolicy implements ParkingCostPolicy {

    private long costPer10minute;

    public StandardPolicy(long costPer10minute) {
        if (costPer10minute < 0) {
            throw new RuntimeException("0보다 작을 수 없습니다");
        }
        this.costPer10minute = costPer10minute;
    }

    @Override
    public long getCostPer10min() {
        return costPer10minute;
    }

    @Override
    public long calculateCost(Duration between) {
        long minutes = between.toMinutes();

        long cost = (minutes / 10) * costPer10minute;

        double leftMinutes = minutes % 10;
        double percent = (leftMinutes / 10.0) * 100.0;
        long additional = Math.round(costPer10minute * percent / 100.0);

        cost += additional;

        return cost;
    }
}
