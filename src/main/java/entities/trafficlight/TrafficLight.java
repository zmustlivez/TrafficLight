package entities.trafficlight;

import entities.Status;
import entities.signal.TrafficSignal;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

@Getter
@Setter
public abstract class TrafficLight {
    private final UUID id;
    private String name;
    private Duration duration;
    private Status status;
    private List<Status> statusList;
    private Queue trafficLightsQueue;

    public TrafficLight(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public abstract void changeSignalRedToGreen(TrafficSignal trafficSignal);

    public abstract void changeSignalGreenToRed(TrafficSignal trafficSignal);


}
