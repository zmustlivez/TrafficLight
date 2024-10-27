package entities.camera;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;

@Slf4j
public abstract class Camera {
    private final UUID id;
    private String name;
    private int queue = 0;

    public Camera(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    /**
     * Метод генерирует случайное количество пешеходов или машин, которое видит камера на перекрестке
     */

    public void recognize(String nameTrafficLight) {
        int rnd = (int) (Math.random() * 11);
        this.queue += rnd;
        log.info("Очередь перед светофором {} сгенерирована: {}", nameTrafficLight, queue);

    }

    public int getQueue() {
        return queue;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Camera camera = (Camera) object;
        return queue == camera.queue && Objects.equals(id, camera.id) && Objects.equals(name, camera.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, queue);
    }
}
