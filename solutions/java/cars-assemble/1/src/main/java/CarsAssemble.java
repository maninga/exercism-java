public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        double ajustedRate = 221 * speed;
        if (speed >= 10) {
            ajustedRate *= 0.77;
        } else if (speed >= 9) {
            ajustedRate *= 0.8;
        } else if (speed >= 5) {
            ajustedRate *= 0.9;
        }
        return ajustedRate;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (this.productionRatePerHour(speed) / 60);
    }
}
