package entities.trafficlight;

import entities.camera.CarCamera;
import entities.signal.CarTrafficSignal;
import entities.signal.TrafficSignal;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import utils.PropertiesUtil;

/**
 * Класс автомобильного светофора
 */
@Slf4j
@Getter
@Setter

public class CarTrafficLight extends TrafficLight {
    /**
     * Если нужно включать зелёный свет пешеходам одновременно с машинами, то создавать светофор внутри светофора
     * автомобильного, иначе создавать отдельно
     */

    private WalkerTrafficLight walkerTrafficLight;

    public CarTrafficLight(String name, CarCamera carCamera) {
        super(name, carCamera);
        this.priorityOfCar = Double.parseDouble(PropertiesUtil.getProperty("priorityOfCar"));
        this.getStatus().setSignal(CarTrafficSignal.RED);
    }

    public CarTrafficLight(String name, CarCamera carCamera, WalkerTrafficLight walkerTrafficLight) {
        this(name, carCamera);
        this.walkerTrafficLight = walkerTrafficLight;
    }

    /**
     * Метод меняет цвет автосветофора с красного на зеленый по очереди
     * с целью, чтобы в вызывающем методе можно было делать паузу перед и после
     * желтого света
     */
    @Override
    public void changeSignalRedToGreen(TrafficSignal signal) {
        if (signal == CarTrafficSignal.YELLOW) {
            this.getStatus().setSignal(CarTrafficSignal.GREEN);
            log.info("Yellow carTrafficLight is now green");
        }
        if (signal == CarTrafficSignal.RED) {
            this.getStatus().setSignal(CarTrafficSignal.YELLOW);
            log.info("Red carTrafficLight is now yellow");
        }
    }

    /**
     * Метод меняет цвет автосветофора с зеленого на красный по очереди
     * с целью, чтобы в вызывающем методе можно было делать паузу перед и после
     * желтого света
     */
    @Override
    public void changeSignalGreenToRed(TrafficSignal signal) {
        if (signal == CarTrafficSignal.YELLOW) {
            this.getStatus().setSignal(CarTrafficSignal.RED);
            log.info("Yellow CarTrafficLight is now RED");
        }
        if (signal == CarTrafficSignal.GREEN) {
            this.getStatus().setSignal(CarTrafficSignal.YELLOW);
            log.info("Yellow carTrafficLight is now YELLOW");
        }
    }


}
