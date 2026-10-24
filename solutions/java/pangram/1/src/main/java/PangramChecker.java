import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

    private final Set<Character> alphabet;

    PangramChecker() {
        alphabet = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++)
            alphabet.add(c);
    }

    public boolean isPangram(String input) {
        String inputLowercase = input.toLowerCase();
        for (int i=0; i< inputLowercase.length(); i++) {
            char c = inputLowercase.charAt(i);
            alphabet.remove(c);
            if (alphabet.isEmpty())
                return true;
        }
        return false;
    }
}
