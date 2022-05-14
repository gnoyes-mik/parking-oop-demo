package domain.policy;

import java.time.Duration;

public interface ParkingCostPolicy {
    long getCostPer10min();

    long calculateCost(Duration between);
}
