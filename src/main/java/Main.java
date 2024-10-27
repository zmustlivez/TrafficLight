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
    }


    static List<TrafficLight> createCarTrafficLights() {
        return new ArrayList<>() {
            {
                add( new CarTrafficLight("carTrafficLight1",  new CarCamera("Cam11"),
                        new WalkerTrafficLight("walkerTl1", new WalkerCamera("wCam1"))));
//                add( new CarTrafficLight("carTrafficLight2",  new CarCamera("Cam12")));
//                add( new CarTrafficLight("carTrafficLight3",  new CarCamera("Cam13")));
//                add( new CarTrafficLight("carTrafficLight4",  new CarCamera("Cam14")));


                Camera walkCam1 = new WalkerCamera("Cam1");
                Camera walkCam2 = new WalkerCamera("Cam2");
                Camera walkCam3 = new WalkerCamera("Cam3");
                Camera walkCam4 = new WalkerCamera("Cam4");
                Camera walkCam5 = new WalkerCamera("Cam5");
                Camera walkCam6 = new WalkerCamera("Cam6");
                Camera walkCam7 = new WalkerCamera("Cam7");
                Camera walkCam8 = new WalkerCamera("Cam8");

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
