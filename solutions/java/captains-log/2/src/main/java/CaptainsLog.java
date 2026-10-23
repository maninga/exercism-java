import java.util.Random;

class CaptainsLog {

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};
    private Random random;

    CaptainsLog(Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        int planetIndex = this.random.nextInt(this.PLANET_CLASSES.length);
        return this.PLANET_CLASSES[planetIndex];
    }

    String randomShipRegistryNumber() {
        String registryPrefix = "NCC-";
        int minNumber = 1000;
        int maxShips = 9000;
        int shipNumber = this.random.nextInt(maxShips);
        return registryPrefix + (minNumber + shipNumber);
    }

    double randomStardate() {
        int minStartDate = 41000;
        int multiplicator = 1000;
        return minStartDate + multiplicator * this.random.nextDouble();
    }
}
