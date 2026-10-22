public class ExperimentalRemoteControlCar implements RemoteControlCar {
    private final int unitsByDrive = 20;
    private int drivenDistance = 0;

    public void drive() {
        this.drivenDistance += this.unitsByDrive;
    }

    public int getDistanceTravelled() {
        return this.drivenDistance;
    }
}
