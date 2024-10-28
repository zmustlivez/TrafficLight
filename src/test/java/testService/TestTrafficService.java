package testService;

import entities.camera.CarCamera;
import entities.trafficlight.CarTrafficLight;
import entities.trafficlight.TrafficLight;
import org.junit.jupiter.api.Test;
import service.TrafficServiceImpl;
import service.TraffisService;

import java.util.List;

public class TestTrafficService {
    private final TraffisService traffisService = new TrafficServiceImpl();
    private final TrafficLight carTrafficLight = new CarTrafficLight("carTrafficLight",
            new CarCamera("CarCamera"));
    private final TrafficLight carTrafficLight1 = new CarTrafficLight("carTrafficLight",
            new CarCamera("CarCamera"));
    private List<TrafficLight> listCarTrafficLights;
    private List<TrafficLight> listWalkerTrafficLights;
    @Test
    void start(List<TrafficLight> listCarTrafficLight) {

    }

    @Test
    void createQueue(TrafficLight trafficLight) {
    }

    @Test
    void decreaseQueue(TrafficLight trafficLight) {
    }

    @Test
    void changeSignalRedToGreen(TrafficLight trafficLight) {
    }

    @Test
    void changeSignalGreenToRed(TrafficLight trafficLight) {
    }

    @Test
    void sendStatus(TrafficLight trafficLight) {
    }
}
