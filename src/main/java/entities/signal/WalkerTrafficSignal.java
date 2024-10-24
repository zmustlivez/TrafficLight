package entities.signal;

public enum WalkerTrafficSignal implements TrafficSignal {
    WALK,
    STOP;

    @Override
    public String getSignal() {
        return this.name();
    }
}
