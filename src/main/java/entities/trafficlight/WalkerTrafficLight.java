package entities.trafficlight;

import entities.camera.WalkerCamera;
import entities.signal.TrafficSignal;
import entities.signal.WalkerTrafficSignal;
import lombok.Getter;
import lombok.Setter;
import utils.PropertiesUtil;

/**
 * Класс пешеходного светофора
 */
@Getter
@Setter
public class WalkerTrafficLight extends TrafficLight {

    public WalkerTrafficLight(String name, WalkerCamera walkerCamera) {
        super(name, walkerCamera);
        this.priorityOfWalker = Double.parseDouble(PropertiesUtil.getProperty("priorityOfWalker"));
    }

    /**
     * Метод меняет цвет пешеходногосветофора с красного на зелёный по очереди
     */
    @Override
    public void changeSignalRedToGreen(TrafficSignal signal) {
        if (signal == WalkerTrafficSignal.STOP) {
            this.getStatus().setSignal(WalkerTrafficSignal.WALK);
        }
    }

    /**
     * Метод меняет цвет пешеходногосветофора с зелёного на красный по очереди
     */
    @Override
    public void changeSignalGreenToRed(TrafficSignal signal) {
        if (signal == WalkerTrafficSignal.WALK) {
            this.getStatus().setSignal(WalkerTrafficSignal.STOP);
        }
    }

    /**
     * @param query - количество человек в очереди перед светофором
     * @return - возвращает длительность включения зеленого света светофора
     */
    @Override
    public double getGreenTime(int query) {
        return query * Double.parseDouble(PropertiesUtil.getProperty("timeOneWalker"));
    }
}
