package entities.trafficlight;

import entities.Status;
import entities.camera.Camera;
import entities.signal.TrafficSignal;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

@Getter
@Setter
public abstract class TrafficLight {
    private final UUID id;
    private String name;

    //    У каждого светофора своя камера
    private Camera camera;

    // продолжительность
    private Duration duration;

    //    статус самого светофора
    private Status status;

    //  очередь задач данного светофора
    private Queue<Status> statusList;

    //    список статусов остальных светофоров
    private Queue<List<Status>> otherTrafficLights;
    protected double priorityOfWalker;
    protected double priorityOfCar;

    public TrafficLight(String name, Camera camera) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.camera = camera;
        this.duration = Duration.ofSeconds(1);
        this.status = new Status();
        this.statusList = new LinkedList<>();
        this.otherTrafficLights = new LinkedList<>();
    }

    public abstract void changeSignalRedToGreen(TrafficSignal trafficSignal);

    public abstract void changeSignalGreenToRed(TrafficSignal trafficSignal);

    public abstract double getGreenTime(int query);

}
