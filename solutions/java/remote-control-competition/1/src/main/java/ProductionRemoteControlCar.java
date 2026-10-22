class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private final int unitsByDrive = 10;
    private int drivenDistance = 0;
    private int numberOfVictories = 0;

    public void drive() {
        this.drivenDistance += this.unitsByDrive;
    }

    public int getDistanceTravelled() {
        return this.drivenDistance;
    }

    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public int compareTo(ProductionRemoteControlCar o) {
        //return this.getNumberOfVictories().compareTo(o.getNumberOfVictories());
        return Integer.compare(this.getNumberOfVictories(), o.getNumberOfVictories());
    }
}
