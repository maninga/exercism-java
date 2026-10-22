public class JedliksToyCar {
    private int drivenDistance = 0;
    private int batteryState = 100;

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + drivenDistance + " meters";
    }

    public String batteryDisplay() {
        if (batteryState > 0)
            return "Battery at " + batteryState + "%";
        return "Battery empty";
    }

    public void drive() {
        if (batteryState > 0) {
            drivenDistance += 20;
            batteryState--;
        }
    }
}
