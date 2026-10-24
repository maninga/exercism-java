public class Say {

    private static final String[] UNITS = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };
    private static final String[] TEENS = {
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    private static final String[] TENS = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    private static final String[] LEVEL_NAMES = {
        "", "thousand", "million", "billion"
    };

    // fonction de traitement pour un bloc à 3 chiffres (0 - 999)
    private String translateLevel(int number) {
        String current = "";

        // 1. Gestion des centaines
        if (number >= 100) {
            current += UNITS[number / 100] + " hundred";
            number %= 100;
            if (number > 0) current += " ";
        }

        // 2. Gestion des dizaines et unités
        if (number >= 20) {
            current += TENS[number / 10];
            if (number % 10 > 0) {
                // Le fameux tiret anglais
                current += "-" + UNITS[number % 10];
            }
        } else if (number >= 10) {
            // Cas spécial 10-19
            current += TEENS[number - 10];
        } else if (number > 0) {
            current += UNITS[number];
        }

        return current;
    }
    public String say(long number) {
        if (number < 0)
            throw new IllegalArgumentException("numbers below zero are out of range");
        if (number > 999_999_999_999L)
            throw new IllegalArgumentException("numbers above 999,999,999,999 are out of range");

        if (number == 0) return "zero";
        
        String result = "";
        int levelIndex = 0;

        // On progresse de droite à gauche par blocs de 1000
        while (number > 0) {
            int level = (int) (number % 1000);
            
            if (level != 0) {
                String levelText = translateLevel(level);
                String levelName = LEVEL_NAMES[levelIndex];
                
                // On assemble le bloc avec son niveau (ex: "two million")
                String combined = levelText + (levelName.isEmpty() ? "" : " " + levelName);
                result = combined + (result.isEmpty() ? "" : " " + result);
            }
            
            number /= 1000;
            levelIndex++;
        }

        return result;
    }
}
