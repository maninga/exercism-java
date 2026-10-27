import java.util.List;

class DiamondPrinter {

    private static char SPACE = ' ';

    List<String> printToList(char a) {
        char c = Character.toUpperCase(a);
        int distance = c - 'A';
        int n = distance * 2 + 1;
        int m = distance + 1;
        
        // 1. Initialisation du tableau de résultat
        String[] diamondHead = new String[m];
        String[] diamondFull = new String[n];
        
        // 2. Génération de chaque chaîne
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            // Déterminer la lettre de la ligne actuelle ('A', puis 'B', puis 'C'...)
            char currentLetter = (char) ('A' + i);
            for (int j = 0; j < m; j++) {
                // La lettre se rapproche du début à mesure que 'i' augmente
                sb.append(j == (distance - i) ? currentLetter : SPACE);
            }
            // Prendre tout sauf le dernier caractère pour le miroir
            StringBuilder mirror = new StringBuilder(sb.substring(0, sb.length() - 1));
            mirror.reverse();
            sb.append(mirror);
            diamondHead[i] = sb.toString();
        }

        System.arraycopy(diamondHead, 0, diamondFull, 0, m);
        // 3. Remplir la fin en lisant le tableau d'origine à l'envers
        int mirrorIndex = m - 2;
        int cursor = m;

        for (int i = mirrorIndex; i >= 0; i--) {
            diamondFull[cursor] = diamondHead[i];
            cursor++;
        }

        return List.of(diamondFull);
    }
}
