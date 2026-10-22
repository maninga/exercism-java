class NeedForSpeed {
    private int speed = 0;
    private int batteryDrain = 0;
    private int drivenDistance = 0;
    private int batteryState = 100;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public boolean batteryDrained() {
        return this.batteryState < this.batteryDrain;
    }

    public int distanceDriven() {
        return this.drivenDistance;
    }

    public int distanceRunnable() {
        return this.speed * this.batteryState / this.batteryDrain;
    }

    public void drive() {
        if (!this.batteryDrained()) {
            this.drivenDistance += this.speed;
            this.batteryState -= this.batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }
}

class RaceTrack {
    private int raceDistance = 0;
    RaceTrack(int distance) {
        this.raceDistance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        return this.raceDistance <= car.distanceRunnable();
    }
}
