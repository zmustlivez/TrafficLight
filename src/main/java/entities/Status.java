package entities;

import entities.signal.TrafficSignal;

public class Status {
    private String id;
    private String name;
    private String quantityWalkers;
    private String quantityCars;
    private int timer;
    private TrafficSignal signal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantityWalkers() {
        return quantityWalkers;
    }

    public void setQuantityWalkers(String quantityWalkers) {
        this.quantityWalkers = quantityWalkers;
    }

    public String getQuantityCars() {
        return quantityCars;
    }

    public void setQuantityCars(String quantityCars) {
        this.quantityCars = quantityCars;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public TrafficSignal getSignal() {
        return signal;
    }

    public void setSignal(TrafficSignal signal) {
        this.signal = signal;
    }
}
