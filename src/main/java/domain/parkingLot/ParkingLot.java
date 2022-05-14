package domain.parkingLot;

import domain.car.Car;
import domain.parkingLot.dto.CarParkingInfo;
import domain.parkingLot.dto.ParkingReceipt;

public interface ParkingLot {
    /**
     * 주차장으로 차량이 진입한다
     */
    CarParkingInfo enter(Car car);

    /**
     * 주차장에서 차량이 퇴장한다
     */
    CarParkingInfo leave(Car car);

    /**
     * 주차칸에 차량을 주차한다
     */
    void park(CarParkingInfo info);

    /**
     * 주차칸에서 차량이 나간다
     */
    void moveOut(CarParkingInfo info);

    /**
     * 주차 요금을 계산한다
     */
    ParkingReceipt calculate(CarParkingInfo info);

    /**
     * 주차장의 현재 주차 현황을 보여준다
     */
    void show();
}
