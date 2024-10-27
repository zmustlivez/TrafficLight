package service;

import entities.trafficlight.CarTrafficLight;
import entities.trafficlight.TrafficLight;
import lombok.extern.slf4j.Slf4j;
import utils.PropertiesUtil;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TrafficServiceImpl implements TraffisService {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void start(List<TrafficLight> carTrafficLightIncludeWalkerTL, List<TrafficLight> walkerTrafficLightList) {
        for (TrafficLight trafficLight : carTrafficLightIncludeWalkerTL) {
            createQueue(trafficLight);
        }
        if (!walkerTrafficLightList.isEmpty()) {
            for (TrafficLight walkerTrafficLight : walkerTrafficLightList) {
                createQueue(walkerTrafficLight);
            }
        }

        for (TrafficLight carTrafficLight : carTrafficLightIncludeWalkerTL) {
            if (carTrafficLight.getCamera().getQueue() != 0) {
                log.info("Color for {} ", carTrafficLight.getName());
                changeSignalRedToGreen(carTrafficLight);
                scheduler.schedule(() -> log.info("Wait {} second to green light",
                                PropertiesUtil.getProperty("timeToGreenLight")),
                        Integer.parseInt(PropertiesUtil.getProperty("timeToGreenLight")),
                        TimeUnit.SECONDS);
                if (((CarTrafficLight) carTrafficLight).getWalkerTrafficLight() != null) {
                    changeSignalRedToGreen(((CarTrafficLight) carTrafficLight).getWalkerTrafficLight());
                }
                changeSignalGreenToRed(carTrafficLight);
            }
        }
        if(!walkerTrafficLightList.isEmpty()) {
            for (TrafficLight trafficLight : walkerTrafficLightList) {
                // сделать в методах changeToGreen/changeToRed распознавание пешеходного и автосветофора, так как у пешика нет желтого
                changeSignalRedToGreen(trafficLight);
                //TODO сделать отдельное время для паузы между переключением у пешеходных светоофоров
                scheduler.schedule(() -> log.info("Wait {} second to green light",
                                PropertiesUtil.getProperty("timeToGreenLight")),
                        Integer.parseInt(PropertiesUtil.getProperty("timeToGreenLight")),
                        TimeUnit.SECONDS);

            }
        }
    }

    @Override
    public void createQueue(TrafficLight trafficLight) {
        trafficLight.getCamera().recognize(trafficLight.getName());
    }


    @Override
    public void changeSignalRedToGreen(TrafficLight trafficLight) {
        trafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal());
        scheduler.schedule(() -> trafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal()),
                Integer.parseInt(PropertiesUtil.getProperty("pauseBetweenYellowGreenColor")),
                TimeUnit.SECONDS);
    }

    @Override
    public void changeSignalGreenToRed(TrafficLight trafficLight) {
        trafficLight.changeSignalGreenToRed(trafficLight.getStatus().getSignal());
        scheduler.schedule(() -> trafficLight.changeSignalGreenToRed(trafficLight.getStatus().getSignal()),
                Integer.parseInt(PropertiesUtil.getProperty("pauseBetweenYellowRedColor")),
                TimeUnit.SECONDS);
    }

    @Override
    public void sendStatus() {

    }
}
