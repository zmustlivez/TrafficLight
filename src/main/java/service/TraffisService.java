package service;

import entities.trafficlight.TrafficLight;

import java.util.List;

public interface TraffisService {
    void start(List<TrafficLight> listTrafficLight, List<TrafficLight> walkerTrafficLightList);

    void createQueue(TrafficLight trafficLight);
    void changeSignalRedToGreen(TrafficLight trafficLight);
    void changeSignalGreenToRed(TrafficLight trafficLight);

    void sendStatus();
}
