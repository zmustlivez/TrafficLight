package entities.signal;

public enum CarTrafficSignal implements TrafficSignal {
    RED,
    YELLOW,
    GREEN;


    @Override
    public String getSignal() {
        return this.name();
    }
}
