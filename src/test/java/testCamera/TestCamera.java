package testCamera;


import entities.camera.Camera;
import entities.camera.WalkerCamera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCamera {
    Camera wCamera = new WalkerCamera("");
    Camera cCamera = new WalkerCamera("");

    @Test
    public void recognize() {
        wCamera.recognize();
        int walkerCamera = wCamera.getQueue();
        Assertions.assertTrue(walkerCamera >= 0 && walkerCamera <= 10,
                "Значение должно быть от 0 до 10 -" + walkerCamera);
        cCamera.recognize();
        int carCamera = cCamera.getQueue();
        Assertions.assertTrue(carCamera >= 0 && carCamera <= 10,
                "Значение должно быть от 0 до 10 -" + carCamera);

    }
}
