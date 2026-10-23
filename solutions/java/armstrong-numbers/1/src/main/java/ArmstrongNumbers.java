class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        int power = String.valueOf(numberToCheck).length();
        int sum = 0;
        int numberChecked = 0;
        for (int i=power-1; i>=0; i--) {
            int units = (int) Math.pow(10, i);
            int number = (numberToCheck - numberChecked) / units;
            sum += Math.pow(number, power);
            numberChecked += number * units;
        }
        return sum == numberToCheck;
    }

}
