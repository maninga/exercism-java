import java.util.List;

class SqueakyClean {
    private static final List<Character> LEET_NUMBERS = List.of('4', '3', '0', '1', '7');
    private static final List<Character> LEET_LETTERS = List.of('a', 'e', 'o', 'l', 't');

    static String clean(String identifier) {
        StringBuilder squeakyBuilder = new StringBuilder();
        char[] chArray = identifier.toCharArray();
        int i = 0;
        while ( i++<chArray.length) {
            char currentChar = SqueakyClean.leetspeakConvert(chArray[i-1]);

            if (currentChar == '-')
                if (i++<chArray.length && Character.isLetter(currentChar = SqueakyClean.leetspeakConvert(chArray[i-1]))) {
                    squeakyBuilder.append(Character.toUpperCase(currentChar));
                    continue;
                }
                    
            if (Character.isWhitespace(currentChar))
                squeakyBuilder.append('_');
            else if (Character.isLetter(currentChar))
                squeakyBuilder.append(currentChar);
        }
        return squeakyBuilder.toString();
    }

    static char leetspeakConvert(char leet) {
        if (LEET_NUMBERS.contains(leet))
            return LEET_LETTERS.get(LEET_NUMBERS.indexOf(leet));
        return leet;
    }
}
