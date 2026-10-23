class Leap {

    boolean isLeapYear(int year) {
        boolean isMultipleOf4 = year % 4 == 0;
        boolean isMultipleOf100 = year % 100 == 0;
        boolean isMultipleOf400 = year % 400 == 0;
        return isMultipleOf400 || isMultipleOf4 && !isMultipleOf100;
    }

}
