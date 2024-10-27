package testCamera;

import entities.Status;
import entities.camera.CarCamera;
import entities.camera.WalkerCamera;
import entities.signal.CarTrafficSignal;
import entities.signal.TrafficSignal;
import entities.signal.WalkerTrafficSignal;
import entities.trafficlight.CarTrafficLight;
import entities.trafficlight.WalkerTrafficLight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestChangeTrafficLight {
    TrafficSignal carTrafficSignalRed = CarTrafficSignal.RED;
    TrafficSignal carTrafficSignalGreen = CarTrafficSignal.GREEN;
    TrafficSignal walkerTrafficSignalRed = WalkerTrafficSignal.STOP;
    TrafficSignal walkerTrafficSignalGreen = WalkerTrafficSignal.WALK;

    CarTrafficLight carTrafficLight;
    WalkerTrafficLight walkerTrafficLight;

    @Test
    public void testChangeRedToGreen() {
        if (carTrafficLight == null) {
            carTrafficLight = new CarTrafficLight("", new CarCamera(""));
        }
        carTrafficLight.setStatus(new Status());
        carTrafficLight.getStatus().setSignal(carTrafficSignalRed);
        carTrafficLight.changeSignalRedToGreen(this.carTrafficLight
                .getStatus()
                .getSignal());
        carTrafficLight.changeSignalRedToGreen(this.carTrafficLight
                .getStatus()
                .getSignal());
        Assertions.assertEquals(carTrafficLight.getStatus().getSignal(), carTrafficSignalGreen,
                "Значение должно быть GREEN" + carTrafficLight.getStatus().getSignal());

        if (walkerTrafficLight == null) {
            walkerTrafficLight = new WalkerTrafficLight("", new WalkerCamera(""));
        }
        walkerTrafficLight.setStatus(new Status());
        walkerTrafficLight.getStatus().setSignal(walkerTrafficSignalRed);
        walkerTrafficLight.changeSignalRedToGreen(this.walkerTrafficLight
                .getStatus()
                .getSignal());
        Assertions.assertEquals(walkerTrafficLight.getStatus().getSignal(), walkerTrafficSignalGreen,
                "Значение должно быть WALK" + walkerTrafficLight.getStatus().getSignal());

    }

    @Test
    public void testChangeGreenToRed() {
        if (carTrafficLight == null) {
            carTrafficLight = new CarTrafficLight("",  new CarCamera(""));
        }
        carTrafficLight.setStatus(new Status());
        carTrafficLight.getStatus().setSignal(carTrafficSignalGreen);
        carTrafficLight.changeSignalGreenToRed(this.carTrafficLight
                .getStatus()
                .getSignal());
        carTrafficLight.changeSignalGreenToRed(this.carTrafficLight
                .getStatus()
                .getSignal());
        Assertions.assertTrue(carTrafficLight.getStatus().getSignal()
                        .equals(carTrafficSignalRed),
                "Значение должно быть RED" + carTrafficLight.getStatus().getSignal());

        if (walkerTrafficLight == null) {
            walkerTrafficLight = new WalkerTrafficLight("",  new WalkerCamera(""));
        }
        walkerTrafficLight.setStatus(new Status());
        walkerTrafficLight.getStatus().setSignal(walkerTrafficSignalGreen);
        walkerTrafficLight.changeSignalGreenToRed(this.walkerTrafficLight
                .getStatus()
                .getSignal());
        Assertions.assertTrue(walkerTrafficLight.getStatus().getSignal()
                        .equals(walkerTrafficSignalRed),
                "Значение должно быть STOP" + walkerTrafficLight.getStatus().getSignal());

    }
}
