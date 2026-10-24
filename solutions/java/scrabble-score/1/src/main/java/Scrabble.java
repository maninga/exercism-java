class Scrabble {

    private int score;

    Scrabble(String word) {
        String temp = valuableWord(word);
        score = 0;
        for (int i=0; i<word.length(); i++)
            score += letterValue(temp.charAt(i));
    }

    String valuableWord(String word) {
        return word
            .toUpperCase()
            .replaceAll("[EIOULNRST]", "A")
            .replace("G", "D")
            .replaceAll("[CMP]", "B")
            .replaceAll("[HVWY]", "F")
            .replace("X", "J")
            .replace("Z", "Q");
    }

    int letterValue(char letter) {
        switch(letter) {
            case 'A':
                return 1;
            case 'D':
                return 2;
            case 'B':
                return 3;
            case 'F':
                return 4;
            case 'K':
                return 5;
            case 'J':
                return 8;
            case 'Q':
                return 10;
            default:
                throw new IllegalArgumentException("Invalid letter in word");
        }
    }

    int getScore() {
        return score;
    }

}
