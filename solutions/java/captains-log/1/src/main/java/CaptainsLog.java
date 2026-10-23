import java.util.Random;

class CaptainsLog {

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};
    private static final String SHIP_REGISTRY_PREFIX = "NCC-";
    private static final int SHIP_REGISTRY_MIN_NUMBER = 1000;
    private static final int SHIP_REGISTRY_MAX_NUMBER = 9000;
    private static final int STELLAR_DATE_MIN_START_DATE = 41000;
    private static final int STELLAR_DATE_MULTIPLICATOR = 1000;
    private Random random;

    CaptainsLog(Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        int planetIndex = this.random.nextInt(this.PLANET_CLASSES.length);
        return this.PLANET_CLASSES[planetIndex];
    }

    String randomShipRegistryNumber() {
        int shipNumber = this.random.nextInt(this.SHIP_REGISTRY_MAX_NUMBER);
        return this.SHIP_REGISTRY_PREFIX + (this.SHIP_REGISTRY_MIN_NUMBER + shipNumber);
    }

    double randomStardate() {
        return this.STELLAR_DATE_MIN_START_DATE + this.STELLAR_DATE_MULTIPLICATOR * this.random.nextDouble();
    }
}
