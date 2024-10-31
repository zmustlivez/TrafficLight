import entities.camera.Camera;
import entities.camera.CarCamera;
import entities.camera.WalkerCamera;
import entities.trafficlight.CarTrafficLight;
import entities.trafficlight.TrafficLight;
import entities.trafficlight.WalkerTrafficLight;
import service.TrafficServiceImpl;
import service.TraffisService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TraffisService traffisService = new TrafficServiceImpl();
        traffisService.start(createCarTrafficLights(), createWalkerTrafficLights());
        Boolean b = new Boolean("/true");
        System.out.println("boolean" + b);
        int i =5;
        i=i++ + ++i;
        System.out.println(i);
    }


    static List<TrafficLight> createCarTrafficLights() {
        return new ArrayList<>() {
            {
                add(new CarTrafficLight("carTrafficLight1", new CarCamera("Cam11"),
                        new WalkerTrafficLight("walkerTl1", new WalkerCamera("wCam1"))));
//                add( new CarTrafficLight("carTrafficLight2",  new CarCamera("Cam12")));
//                add( new CarTrafficLight("carTrafficLight3",  new CarCamera("Cam13")));
//                add( new CarTrafficLight("carTrafficLight4",  new CarCamera("Cam14")));


            }
        };
    }

    static List<TrafficLight> createWalkerTrafficLights() {
        return new ArrayList<>() {
            {
//                add(new WalkerTrafficLight("walkerTrafficLight1", new WalkerCamera("walkerCam1")));
            }
        };
    }


}
