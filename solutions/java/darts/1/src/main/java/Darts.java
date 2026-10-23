class Darts {
    int score(double xOfDart, double yOfDart) {
        double distanceToCenter = Math.sqrt(Math.pow(xOfDart, 2) + Math.pow(yOfDart, 2));
        if (distanceToCenter > 10)
            return 0;
        if (distanceToCenter > 5)
            return 1;
        if (distanceToCenter > 1)
            return 5;
        return 10;
    }
}
