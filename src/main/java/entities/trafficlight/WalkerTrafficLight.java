package entities.trafficlight;

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

    public WalkerTrafficLight(String name) {
        super(name);
        this.priorityOfWalker = Double.parseDouble(PropertiesUtil.getProperty("priorityOfWalker"));
    }

    /**
     * Метод меняет цвет пешеходногосветофора с красного на зелёный по очереди
     */
    @Override
    public void changeSignalRedToGreen(TrafficSignal signal) {
        if (signal==WalkerTrafficSignal.STOP) {
            this.getStatus().setSignal(WalkerTrafficSignal.WALK);
        }
    }

    /**
     * Метод меняет цвет пешеходногосветофора с зелёного на красный по очереди
     */
    @Override
    public void changeSignalGreenToRed(TrafficSignal signal) {
        if (signal==WalkerTrafficSignal.WALK) {
            this.getStatus().setSignal(WalkerTrafficSignal.STOP);
        }

    }
}
