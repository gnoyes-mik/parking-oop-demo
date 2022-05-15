package domain.parkingLot;

import domain.car.Car;
import domain.parkingBox.ParkingBox;
import domain.parkingBox.map.MapStrategy;
import domain.parkingLot.dto.CarParkingInfo;
import domain.parkingLot.dto.ParkingLotMapInfo;
import domain.parkingLot.dto.ParkingReceipt;
import domain.policy.ParkingCostPolicy;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class StandardParkingLot implements ParkingLot {

    private ParkingBox[][] parkingLot;
    private ParkingCostPolicy costPolicy;
    private final int MAX_FLOOR;
    private final int MAX_NO;
    private final String PARKING_LOT_NAME = "STANDARD_PARK";

    public StandardParkingLot(ParkingLotMapInfo mapInfo, ParkingCostPolicy costPolicy) {
        validateConstructInfo(mapInfo.getFloor(), mapInfo.getNo(), costPolicy);

        this.parkingLot = generateParkingBoxes(mapInfo);
        this.MAX_FLOOR = mapInfo.getFloor();
        this.MAX_NO = mapInfo.getNo();
        this.costPolicy = costPolicy;
    }

    private void validateConstructInfo(int floor, int no, ParkingCostPolicy costPolicy) {
        if (floor <= 0) {
            throw new IllegalArgumentException("floor 값은 0 이하가 될 수 없습니다");
        }
        if (no <= 0) {
            throw new IllegalArgumentException("no 값은 0 이하가 될 수 없습니다");
        }
        if (Objects.isNull(costPolicy)) {
            throw new IllegalArgumentException("주차 비용 정책이 없습니다");
        }
    }

    private ParkingBox[][] generateParkingBoxes(ParkingLotMapInfo mapInfo) {
        MapStrategy strategy = mapInfo.getStrategy();
        return strategy.createMap(mapInfo.getFloor(), mapInfo.getNo());
    }

    @Override
    public CarParkingInfo find(Car car) {
        validateParam(car);
        return findByCarNum(car.getCarNum());
    }

    private CarParkingInfo findByCarNum(String carNum) {
        for (ParkingBox[] eachFloor : parkingLot) {
            for (ParkingBox parkingBox : eachFloor) {
                if (Objects.isNull(parkingBox)) {
                    continue;
                }
                if (Objects.isNull(parkingBox.getInfo())) {
                    continue;
                }
                Car car = parkingBox.getInfo().getCar();
                if (carNum.equals(car.getCarNum())) {
                    return parkingBox.getInfo();
                }
            }
        }
        return null;
    }

    @Override
    public CarParkingInfo enter(Car car) {
        validateParam(car);
        CarParkingInfo carParkingInfo = new CarParkingInfo(PARKING_LOT_NAME, car);
        carParkingInfo.recordEnterTime();
        return carParkingInfo;
    }

    @Override
    public CarParkingInfo leave(Car car) {
        validateParam(car);
        CarParkingInfo parkedCarInfo = findByCarNum(car.getCarNum());

        if (Objects.isNull(parkedCarInfo)) {
            throw new RuntimeException("차량이 존재하지 않습니다");
        }

        parkedCarInfo.recordLeaveTime();
        return parkedCarInfo;
    }

    @Override
    public void park(CarParkingInfo info) {
        validateParam(info);

        int floor = info.getFloor();
        int no = info.getParkingBoxNo();

        if (parkingLot[floor][no].status()) {
            log.error("{}층 {}번에 차량이 이미 존재합니다", info.getFloor(), info.getParkingBoxNo());
            throw new RuntimeException("차량이 이미 존재합니다");
        }

        parkingLot[floor][no].in(info);

        log.info("차량 번호 [{}]가 {}층 {}번에 주차되었습니다", info.getCar().getCarNum(), info.getFloor(), info.getParkingBoxNo());
    }

    @Override
    public void moveOut(CarParkingInfo info) {
        validateParam(info);

        int floor = info.getFloor();
        int no = info.getParkingBoxNo();

        Car car = info.getCar();
        if (!parkingLot[floor][no].status()) {
            log.error("{}층 {}번에 이미 차량이 존재하지 않습니다", info.getFloor(), info.getParkingBoxNo());
            throw new RuntimeException("주차 칸에 차량이 존재하지 않습니다");
        }

        parkingLot[floor][no].out();
        log.info("차량 번호 [{}]가 출차되었습니다", car.getCarNum());
    }

    private void validateParam(Car car) {
        if (Objects.isNull(car)) {
            throw new IllegalArgumentException("자동차 정보는 필수입니다");
        }
        if (Objects.isNull(car.getCarNum()) || car.getCarNum().isEmpty()) {
            throw new IllegalArgumentException("차량 번호를 다시 확인해주세요");
        }
    }

    private void validateParam(CarParkingInfo info) {
        if (Objects.isNull(info) || Objects.isNull(info.getLocation())) {
            log.error("location 정보는 필수 입니다");
            throw new RuntimeException("location 정보는 필수 입니다");
        }

        int MIN_FLOOR = 1;
        int MIN_NO = 1;
        int floor = info.getFloor();
        int no = info.getParkingBoxNo();
        if (floor < MIN_FLOOR || no < MIN_NO) {
            log.error("floor 또는 no 값이 비정상적으로 작습니다");
            throw new RuntimeException("최솟값보다 작습니다");
        }

        if (floor > MAX_FLOOR || no > MAX_NO) {
            log.error("floor 또는 no 값이 비정상적으로 큽니다");
            throw new RuntimeException("최댓값보다 큽니다");
        }
    }

    @Override
    public ParkingReceipt calculate(CarParkingInfo info) {
        LocalDateTime enterTime = info.getEnterTime();
        LocalDateTime leaveTime = info.getLeaveTime();
        Duration duration = Duration.between(enterTime, leaveTime);

        long cost = costPolicy.calculateCost(duration);

        log.info("[{}] 기간 : {} ~ {} / 비용 : {}(원)", info.getCar().getCarNum(), enterTime, leaveTime, cost);
        return new ParkingReceipt(cost);
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
