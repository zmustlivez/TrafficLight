package entities.trafficlight;

import entities.signal.CarTrafficSignal;
import entities.signal.TrafficSignal;
import lombok.Getter;
import lombok.Setter;
import utils.PropertiesUtil;

/**
 * Класс автомобильного светофора
 */
@Getter
@Setter
public class CarTrafficLight extends TrafficLight {

    public CarTrafficLight(String name) {
        super(name);
        this.priorityOfCar = Double.parseDouble(PropertiesUtil.getProperty("weightOfCar"));
    }

    /**
     * Метод меняет цвет автосветофора с красного на зеленый по очереди
     * с целью, чтобы в вызывающем методе можно было делать паузу перед и после
     * желтого света
     */
    @Override
    public void changeSignalRedToGreen(TrafficSignal signal) {
        if (signal==CarTrafficSignal.YELLOW) {
            this.getStatus().setSignal(CarTrafficSignal.GREEN);
        }
        if (signal==CarTrafficSignal.RED) {
            this.getStatus().setSignal(CarTrafficSignal.YELLOW);
        }
    }

    /**
     * Метод меняет цвет автосветофора с зеленого на красный по очереди
     * с целью, чтобы в вызывающем методе можно было делать паузу перед и после
     * желтого света
     */
    @Override
    public void changeSignalGreenToRed(TrafficSignal signal) {
        if (signal==CarTrafficSignal.YELLOW) {
            this.getStatus().setSignal(CarTrafficSignal.RED);
        }
        if (signal==CarTrafficSignal.GREEN) {
            this.getStatus().setSignal(CarTrafficSignal.YELLOW);
        }
    }
}
