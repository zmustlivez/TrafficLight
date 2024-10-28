package service;

import entities.trafficlight.CarTrafficLight;
import entities.trafficlight.TrafficLight;
import entities.trafficlight.WalkerTrafficLight;
import lombok.extern.slf4j.Slf4j;
import utils.PropertiesUtil;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TrafficServiceImpl implements TraffisService {
    private List<TrafficLight> listCarTrafficLights;
    private List<TrafficLight> listWalkerTrafficLights;
    private CarTrafficLight tempCarTrafficLight;
    private WalkerTrafficLight tempWalkerTrafficLight;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void start(List<TrafficLight> carTrafficLightIncludeWalkerTL, List<TrafficLight> walkerTrafficLightList) {
        listCarTrafficLights = carTrafficLightIncludeWalkerTL;
        listWalkerTrafficLights = walkerTrafficLightList;
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
        if (!walkerTrafficLightList.isEmpty()) {
            for (TrafficLight trafficLight : walkerTrafficLightList) {
                // сделать в методах changeToGreen/changeToRed распознавание пешеходного и автосветофора, так как у пешика нет желтого
//                TODO проверить правильно ли работает распознавание принадлежности объекта trafficlight в методе переключения светофора
                changeSignalRedToGreen(trafficLight);
                //TODO сделать отдельное время для паузы между переключением у пешеходных светофоров
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

    /**
     * Метод уменьшает очередь на случайное число (имитация не всех проехавших машин)
     */
    @Override
    public void decreaseQueue(TrafficLight trafficLight) {
        trafficLight.getCamera().decrease(trafficLight.getName());
    }

    /**
     * Метод проверяет объектом какого класса является @param trafficLight и выбирает соответствующий сигнал
     */
//    TODO Надо написать тесты для проверки правильности распознавания светофора
    @Override
    public void changeSignalRedToGreen(TrafficLight trafficLight) {
        if (trafficLight instanceof CarTrafficLight) {
            CarTrafficLight carTrafficLight = (CarTrafficLight) trafficLight;
            carTrafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal());
            scheduler.schedule(() -> carTrafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal()),
                    Integer.parseInt(PropertiesUtil.getProperty("pauseBetweenYellowGreenColor")),
                    TimeUnit.SECONDS);
        } else if (trafficLight instanceof WalkerTrafficLight) {
            WalkerTrafficLight walkerTrafficLight = (WalkerTrafficLight) trafficLight;
            walkerTrafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal());
            scheduler.schedule(() -> walkerTrafficLight.changeSignalRedToGreen(trafficLight.getStatus().getSignal()),
                    Integer.parseInt(PropertiesUtil.getProperty("pauseBetweenYellowGreenColor")),
                    TimeUnit.SECONDS);
        }
    }


    @Override
    public void changeSignalGreenToRed(TrafficLight trafficLight) {
        trafficLight.changeSignalGreenToRed(trafficLight.getStatus().getSignal());
        scheduler.schedule(() -> trafficLight.changeSignalGreenToRed(trafficLight.getStatus().getSignal()),
                Integer.parseInt(PropertiesUtil.getProperty("pauseBetweenYellowRedColor")),
                TimeUnit.SECONDS);
    }

    /**
     * метод добавляет статус одного светоофора в общий список статусов остальных светофоров
     * @param trafficLight
     */
    //    TODO Надо написать тесты для проверки правильности отправки статуса остальным светофорам
    @Override
    public void sendStatus(TrafficLight trafficLight) {

        if (trafficLight instanceof CarTrafficLight) {
            tempCarTrafficLight = (CarTrafficLight) trafficLight;
            for (TrafficLight carTrafficLight : listCarTrafficLights) {
                if (!tempCarTrafficLight.equals(carTrafficLight)) {
                    carTrafficLight.getStatusList().add(tempCarTrafficLight.getStatus());
                }
            }
        } else if (trafficLight instanceof WalkerTrafficLight) {
            tempWalkerTrafficLight = (WalkerTrafficLight) trafficLight;
            for (TrafficLight walkerTrafficLight : listWalkerTrafficLights) {
                if (!tempWalkerTrafficLight.equals(walkerTrafficLight)) {
                    walkerTrafficLight.getStatusList().add(tempWalkerTrafficLight.getStatus());
                }
            }

        }
    }
}
