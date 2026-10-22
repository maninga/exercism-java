
class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public static int[] getLastWeek() {
        int[] birdsLastWeek = {0, 2, 5, 3, 7, 8, 4};
        return birdsLastWeek;
    }

    public int getToday() {
        return this.birdsPerDay[this.birdsPerDay.length-1];
    }

    public void incrementTodaysCount() {
        this.birdsPerDay[this.birdsPerDay.length-1]++;
    }

    public boolean hasDayWithoutBirds() {
        for (int birdsOnDay: this.birdsPerDay)
            if (birdsOnDay == 0)
                return true;
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int sum = 0;
        int limit = Math.min(numberOfDays, this.birdsPerDay.length);
        for (int i=0; i<limit; i++)
            sum += this.birdsPerDay[i];
        return sum;
    }

    public int getBusyDays() {
        int count = 0;
        for (int birdsOnDay: this.birdsPerDay)
            if (birdsOnDay >= 5)
                count++;
        return count;
    }
}
