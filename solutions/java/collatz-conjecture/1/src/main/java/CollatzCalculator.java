class CollatzCalculator {

    int computeStepCount(int start) {
        if (start < 1)
            throw new IllegalArgumentException("Only positive integers are allowed");
        if (start == 1)
            return 0;
        return 1 + computeStepCount(start % 2 == 0 ? start / 2 : 3 * start + 1);
    }

}
