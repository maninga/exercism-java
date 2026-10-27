class IsbnVerifier {
    private static int digitsInISBN10 = 10;
    
    boolean isValid(String stringToVerify) {
        if (stringToVerify == null || stringToVerify.isEmpty())
            return false;

        int lastIndex = stringToVerify.length() - 1;
        int digitCount = 0;
        int check = 0;
        for (int i = 0; i <= lastIndex; i++) {
            char c = stringToVerify.charAt(i);

            if (Character.isDigit(c)) {
                check += Character.getNumericValue(c) * (digitsInISBN10 - digitCount);
                digitCount++;
            } else if (i == lastIndex && (c == 'X' || c == 'x')) {
                check += 10;
                digitCount++;
            } else if (c != '-') {
                return false;
            }
        }
        if (digitCount != 10)
            return false;

        return check % (digitsInISBN10 + 1) == 0;
    }
}
