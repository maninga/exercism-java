class IsogramChecker {

    boolean isIsogram(String phrase) {
        if (phrase == null)
            return false;

        String lowerCasePhrase = phrase.toLowerCase();
        boolean[] seenCharacters = new boolean[26];
        for (int i = 0; i < lowerCasePhrase.length(); i++) {
            char c = lowerCasePhrase.charAt(i);

            if (Character.isLetter(c)) {
                int cIndex = c - 'a';
                if (seenCharacters[cIndex])
                    return false;
                seenCharacters[cIndex] = true;
            }
        }
        return true;
    }
}
