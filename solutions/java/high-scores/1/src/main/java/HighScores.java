import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HighScores {
    private final List<Integer> allScores;
    private final List<Integer> sortedScores;
    
    public HighScores(List<Integer> highScores) {
        allScores = highScores;
        sortedScores = new ArrayList<>(highScores);
        sortedScores.sort(Collections.reverseOrder());
    }

    List<Integer> scores() {
        return allScores;
    }

    Integer latest() {
        return allScores.getLast();
    }

    Integer personalBest() {
        return sortedScores.getFirst();
    }

    List<Integer> personalTopThree() {
        return sortedScores.subList(0, Math.min(3, sortedScores.size()));
    }

}
