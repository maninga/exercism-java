import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// =========================================================================
// 1. LA CLASSE PRINCIPALE (DOIT PORTER LE NOM DU FICHIER)
// =========================================================================

public class ZebraPuzzle {

    // On déclare nos variables ici pour pouvoir les lire dans toutes les méthodes
    private List<Variable<String>> nationalityVars;
    private List<Variable<String>> drinkVars;
    private List<Variable<String>> petVars;
    private List<Variable<String>> actionVars;

    // Le constructeur : il s'exécute AUTOMATIQUEMENT dès qu'on fera `new ZebraPuzzle()`
    public ZebraPuzzle() {
        // 1. On prépare le problème
        ZebraCSP problemData = new ZebraCSP();
        CSP myCsp = problemData.getCsp();

        // On garde une référence locale sur nos listes de variables
        this.nationalityVars = problemData.getnationalityVars();
        this.drinkVars = problemData.getDrinkVars();
        this.petVars = problemData.getpetVars();

        // 2. On lance la résolution IMMÉDIATEMENT
        CSPSolver solver = new CSPSolver();
        boolean solved = solver.solve(myCsp);

        if (!solved) {
            throw new RuntimeException("Impossible de résoudre l'énigme ! Vérifiez vos contraintes.");
        }
        // Une fois solved == true, toutes les variables ont reçu leur bonne valeur (.getValue())
    }

    // Qui boit de l'eau ?
    public String getWaterDrinker() {
        // On cherche la maison où la boisson est "Water"
        for (int i = 0; i < 5; i++) {
            if ("Water".equals(drinkVars.get(i).getValue())) {
                // On renvoie la nationalité de cette même maison
                return nationalityVars.get(i).getValue();
            }
        }
        return "Unknown";
    }

    // Qui possède le zèbre ?
    public String getZebraOwner() {
        // On cherche la maison où l'animal est "Zebra"
        for (int i = 0; i < 5; i++) {
            if ("Zebra".equals(petVars.get(i).getValue())) {
                // On renvoie la nationalité de cette même maison
                return nationalityVars.get(i).getValue();
            }
        }
        return "Unknown";
    }

}

// =========================================================================
// 2. LES COMPOSANTS DE BASE DU CSP
// =========================================================================

// Représente une caractéristique (ex: Nationalité de la maison 1, Couleur de la maison 2)
class Variable<T> {
    private final String name;
    private final List<T> domain; // Les valeurs possibles (ex: [Rouge, Vert, Ivoire...])
    private T currentValue;       // La valeur actuellement affectée (null si non assignée)

    public Variable(String name, List<T> domain) {
        this.name = name;
        this.domain = new ArrayList<>(domain);
        this.currentValue = null;
    }

    // Getters, Setters, et méthode pour savoir si la variable est assignée
    public boolean isAssigned() { return currentValue != null; }
    public void assign(T value) { this.currentValue = value; }
    public void unassign() { this.currentValue = null; }
    public T getValue() { return currentValue; }
    public List<T> getDomain() { return domain; }
}

interface Constraint {
    // Retourne la liste des variables impliquées dans cette contrainte
    List<Variable<?>> getScope();

    // Vérifie si la contrainte est respectée au vu des assignations actuelles
    // Si une variable de la contrainte n'est pas encore assignée, on considère généralement que c'est valide (en attente)
    boolean isSatisfied();
}

class CSP {
    private final List<Variable<?>> variables = new ArrayList<>();
    private final List<Constraint> constraints = new ArrayList<>();

    public void addVariable(Variable<?> var) { variables.add(var); }
    public void addConstraint(Constraint constraint) { constraints.add(constraint); }

    public List<Variable<?>> getVariables() { return variables; }

    // Utile pour le moteur : vérifier rapidement si une assignation partielle ne viole rien
    public boolean isValid() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied()) {
                return false; // Élagage immédiat !
            }
        }
        return true;
    }
}

// =========================================================================
// 3. LES DÉFINITIONS DES CONTRAINTES (DÉCLARÉES AVANT LEUR UTILISATION)
// =========================================================================

class EqualityConstraint<T> implements Constraint {
    private final Variable<T> var1;
    private final Variable<T> var2;

    public EqualityConstraint(Variable<T> var1, Variable<T> var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public List<Variable<?>> getScope() { return List.of(var1, var2); }

    @Override
    public boolean isSatisfied() {
        // Si l'une des deux n'est pas encore assignée, la contrainte n'est pas encore violée
        if (!var1.isAssigned() || !var2.isAssigned()) {
            return true; 
        }
        return var1.getValue().equals(var2.getValue());
    }
}

class ForbiddenValueConstraint<T> implements Constraint {
    private final Variable<T> variable;
    private final T forbiddenValue;

    public ForbiddenValueConstraint(Variable<T> variable, T forbiddenValue) {
        this.variable = variable;
        this.forbiddenValue = forbiddenValue;
    }

    @Override
    public List<Variable<?>> getScope() { return List.of(variable); }

    @Override
    public boolean isSatisfied() {
        if (!variable.isAssigned()) return true;
        return !variable.getValue().equals(forbiddenValue);
    }
}

class ImplicationConstraint<T, U> implements Constraint {
    private final Variable<T> conditionVar;
    private final T conditionValue;
    private final Variable<U> targetVar;
    private final U targetValue;

    public ImplicationConstraint(Variable<T> conditionVar, T conditionValue, Variable<U> targetVar, U targetValue) {
        this.conditionVar = conditionVar;
        this.conditionValue = conditionValue;
        this.targetVar = targetVar;
        this.targetValue = targetValue;
    }

    @Override
    public List<Variable<?>> getScope() { return List.of(conditionVar, targetVar); }

    @Override
    public boolean isSatisfied() {
        if (!conditionVar.isAssigned() || !targetVar.isAssigned()) return true;
        if (conditionVar.getValue().equals(conditionValue)) {
            return targetVar.getValue().equals(targetValue);
        }
        return true;
    }
}

class FixedPositionConstraint<T> implements Constraint {
    private final Variable<T> variable;
    private final T targetValue;

    // Constructeur : on lui passe la variable de la maison visée et la valeur attendue
    public FixedPositionConstraint(Variable<T> variable, T targetValue) {
        this.variable = variable;
        this.targetValue = targetValue;
    }

    @Override
    public List<Variable<?>> getScope() { 
        return List.of(variable); 
    }

    @Override
    public boolean isSatisfied() {
        // Si elle n'est pas encore assignée, on ne bloque pas
        if (!variable.isAssigned()) return true;
        
        // La contrainte est valide si la valeur courante correspond à la valeur cible
        return variable.getValue().equals(targetValue);
    }
}

class NextToConstraint<T, U> implements Constraint {
    private final List<Variable<T>> listA;
    private final T valueA;
    private final List<Variable<U>> listB;
    private final U valueB;

    public NextToConstraint(List<Variable<T>> listA, T valueA, List<Variable<U>> listB, U valueB) {
        this.listA = listA;
        this.valueA = valueA;
        this.listB = listB;
        this.valueB = valueB;
    }

    @Override
    public List<Variable<?>> getScope() {
        List<Variable<?>> scope = new ArrayList<>();
        scope.addAll(listA);
        scope.addAll(listB);
        return scope;
    }

    @Override
    public boolean isSatisfied() {
        int indexA = -1;
        int indexB = -1;

        // 1. On cherche où se trouvent nos valeurs cibles dans les 5 maisons
        for (int i = 0; i < 5; i++) {
            if (listA.get(i).isAssigned() && listA.get(i).getValue().equals(valueA)) {
                indexA = i;
            }
            if (listB.get(i).isAssigned() && listB.get(i).getValue().equals(valueB)) {
                indexB = i;
            }
        }

        // 2. Si l'une des deux (ou les deux) n'est pas encore placée, la contrainte n'est pas encore violée
        if (indexA == -1 || indexB == -1) {
            return true;
        }

        // 3. Validation mathématique de la proximité : l'écart absolu doit être de strictement 1 maison
        return Math.abs(indexA - indexB) == 1;
    }
}

class AllDifferentConstraint implements Constraint {
    
    // La liste des variables qui doivent toutes avoir une valeur différente
    private final List<? extends Variable<?>> variables;

    // Constructeur : on reçoit la liste des variables à surveiller
    public AllDifferentConstraint(List<? extends Variable<?>> variables) {
        this.variables = variables;
    }

    @Override
    public List<Variable<?>> getScope() {
        // La portée de la contrainte englobe toutes les variables de la liste
        return new ArrayList<>(variables);
    }

    @Override
    public boolean isSatisfied() {
        // Un Set stocke des éléments uniques et rejette automatiquement les doublons
        Set<Object> assignedValues = new HashSet<>();

        for (Variable<?> var : variables) {
            // Si la variable n'est pas encore assignée, on l'ignore pour l'instant
            if (!var.isAssigned()) {
                continue;
            }

            Object value = var.getValue();

            // .add() renvoie false si l'élément existe déjà dans le Set
            if (!assignedValues.add(value)) {
                return false; // Doublon détecté ! Contrainte non respectée.
            }
        }

        // Si on a parcouru toutes les variables sans trouver de doublon
        return true; 
    }
}

// =========================================================================
// 4. LE MODÈLE DU PROBLÈME (ZebraCSP S'APPUIE SUR LES CLASSES CI-DESSUS)
// =========================================================================

class ZebraCSP {
    
    // On déclare le CSP en tant que variable globale de la classe (un "attribut")
    // pour pouvoir le récupérer facilement plus tard
    private final CSP csp;

    // Listes pour garder une référence sur nos variables par catégorie
    private final List<Variable<String>> houseColorVars = new ArrayList<>();
    private final List<Variable<String>> nationalityVars = new ArrayList<>();
    private final List<Variable<String>> petVars = new ArrayList<>();
    private final List<Variable<String>> drinkVars = new ArrayList<>();
    private final List<Variable<String>> actionVars = new ArrayList<>();

    // --- LE CONSTRUCTEUR ---
    // Il s'exécute quand on fait : new ZebraCSP()
    public ZebraCSP() {
        // 1. On instancie l'objet CSP
        this.csp = new CSP();

        // 2. On définit nos domaines de données
        List<String> colors = List.of("Red", "Green", "Ivory", "Yellow", "Blue");
        List<String> nationalities = List.of("English", "Spanish", "Ukrainian", "Norwegian", "Japanese");
        List<String> pets = List.of("Dog", "Snails", "Fox", "Horse", "Zebra");
        List<String> drinks = List.of("Coffee", "Tea", "Milk", "Water", "OrangeJuice");
        List<String> actions = List.of("Dancing", "Painting", "Reading", "PlayingFootball", "PlayingChess");

        // 3. On crée les variables pour les 5 maisons
        for (int i = 1; i <= 5; i++) {
            Variable<String> color = new Variable<>("Color_House_" + i, colors);
            Variable<String> nationality = new Variable<>("Nationality_House_" + i, nationalities);
            Variable<String> pet = new Variable<>("Pet_House_" + i, pets);
            Variable<String> drink = new Variable<>("Drink_House_" + i, drinks);
            Variable<String> action = new Variable<>("Action_House_" + i, actions);

            // On les ajoute au moteur CSP global
            this.csp.addVariable(color);
            this.csp.addVariable(nationality);
            this.csp.addVariable(pet);
            this.csp.addVariable(drink);
            this.csp.addVariable(action);

            // On les range dans nos listes pour créer les contraintes juste après
            houseColorVars.add(color);
            nationalityVars.add(nationality);
            petVars.add(pet);
            drinkVars.add(drink);
            actionVars.add(action);
        }

        // 4. On appelle une méthode privée pour ajouter toutes les contraintes
        setupConstraints();
    }

    // Méthode d'aide pour ne pas surcharger le constructeur
    private void setupConstraints() {
        // Contraintes d'unicité (toutes différentes)
        // 1. Il y a cinq maisons.
        // De plus, chacune des cinq maisons est peinte d'une couleur différente,
        this.csp.addConstraint(new AllDifferentConstraint(houseColorVars));
        // et leurs habitants sont d'origines nationales différentes,
        this.csp.addConstraint(new AllDifferentConstraint(nationalityVars));
        // possèdent des animaux différents,
        this.csp.addConstraint(new AllDifferentConstraint(petVars));
        // boivent des boissons différentes
        this.csp.addConstraint(new AllDifferentConstraint(drinkVars));
        // et pratiquent des loisirs différents.
        this.csp.addConstraint(new AllDifferentConstraint(actionVars));

        // Vos contraintes de phrases (Exemples)
        for (int i = 0; i < 5; i++) {
            // 2. L'Anglais vit dans la maison rouge.
            this.csp.addConstraint(new ImplicationConstraint<>(nationalityVars.get(i), "English", houseColorVars.get(i), "Red"));
            // 3. L'Espagnol possède le chien.
            this.csp.addConstraint(new ImplicationConstraint<>(nationalityVars.get(i), "Spanish", petVars.get(i), "Dog"));
            // 4. La personne qui vit dans la maison verte boit du café.
            this.csp.addConstraint(new ImplicationConstraint<>(houseColorVars.get(i), "Green", drinkVars.get(i), "Coffee"));
            // 5. L'Ukrainien boit du thé.
            this.csp.addConstraint(new ImplicationConstraint<>(nationalityVars.get(i), "Ukrainian", drinkVars.get(i), "Tea"));
            // 7. Le propriétaire de l'escargot aime aller danser.
            this.csp.addConstraint(new ImplicationConstraint<>(petVars.get(i), "Snails", actionVars.get(i), "Dancing"));
            // 8. La personne qui vit dans la maison jaune est peintre.
            this.csp.addConstraint(new ImplicationConstraint<>(houseColorVars.get(i), "Yellow", actionVars.get(i), "Painting"));
            // 13. La personne qui joue au football boit du jus d'orange.
            this.csp.addConstraint(new ImplicationConstraint<>(actionVars.get(i), "PlayingFootball", drinkVars.get(i), "OrangeJuice"));
            // 14. Le Japonais joue aux échecs.
            this.csp.addConstraint(new ImplicationConstraint<>(nationalityVars.get(i), "Japanese", actionVars.get(i), "PlayingChess"));
        }

        // 6. La maison verte se trouve immédiatement à droite de la maison ivoire.
        for (int i = 0; i < 4; i++) {
            this.csp.addConstraint(new ImplicationConstraint<>(houseColorVars.get(i), "Ivory", houseColorVars.get(i + 1), "Green"));
        }
        this.csp.addConstraint(new ForbiddenValueConstraint<>(houseColorVars.get(4), "Ivory"));
        // 9. La personne qui vit dans la maison du milieu boit du lait.
        this.csp.addConstraint(new FixedPositionConstraint<>(drinkVars.get(2), "Milk"));
        // 10. Le Norvégien vit dans la première maison.
        this.csp.addConstraint(new FixedPositionConstraint<>(nationalityVars.get(0), "Norwegian"));
        // 11. La personne qui aime lire vit dans la maison située à côté de celle de la personne qui a le renard.
        this.csp.addConstraint(new NextToConstraint<>(actionVars, "Reading", petVars, "Fox"));
        // 12. La maison du peintre est à côté de la maison où se trouve le cheval.
        this.csp.addConstraint(new NextToConstraint<>(actionVars, "Painting", petVars, "Horse"));
        // 15. Norvégien à côté de Bleue
        this.csp.addConstraint(new NextToConstraint<>(nationalityVars, "Norwegian", houseColorVars, "Blue"));    }

    // Un "Getter" : une méthode publique pour permettre au fichier Main de récupérer le CSP configuré
    public CSP getCsp() {
        return this.csp;
    }

    // Getters pour les listes de variables (utiles pour l'affichage final dans le Main)
    public List<Variable<String>> getnationalityVars() { return nationalityVars; }
    public List<Variable<String>> gethouseColorVars() { return houseColorVars; }
    public List<Variable<String>> getDrinkVars() { return drinkVars; }
    public List<Variable<String>> getpetVars() { return petVars; }
    public List<Variable<String>> getactionVars() { return actionVars; }
}

// =========================================================================
// 5. LE SOLVEUR (ALGORITHME DE EXPLORATION/RETOUR EN ARRIÈRE)
// =========================================================================

class CSPSolver {

    public boolean solve(CSP csp) {
        // 1. Trouver la première variable qui n'a pas encore de valeur
        Variable<String> currentVar = (Variable<String>) selectUnassignedVariable(csp);

        // 2. CONDITION D'ARRÊT : Si toutes les variables sont assignées, 
        // on vérifie si la solution finale respecte toutes les contraintes
        if (currentVar == null) {
            return csp.isValid();
        }

        // 3. LA BOUCLE : On parcourt toutes les valeurs possibles pour cette variable
        for (String value : currentVar.getDomain()) {
            
            // On fait l'hypothèse que la variable prend cette valeur
            currentVar.assign(value);

            // ÉLAGAGE PRÉCOCE : On vérifie si cette hypothèse ne viole pas une contrainte
            if (csp.isValid()) {
                
                // Appel récursif : on demande au solveur de s'occuper de la variable suivante
                boolean result = solve(csp);
                
                // Si l'appel récursif a réussi à aller jusqu'au bout, on propage le succès !
                if (result) {
                    return true;
                }
            }

            // BACKTRACK : Si l'hypothèse a échoué (blocage plus loin dans l'arbre),
            // ou si csp.isValid() était faux, on réinitialise la variable à null 
            // avant de tester la valeur suivante du 'for'
            currentVar.unassign();
        }

        // Si aucune valeur du domaine n'a fonctionné, on retourne false 
        // pour dire au niveau supérieur de l'arbre de faire machine arrière
        return false;
    }

    @SuppressWarnings("unchecked")
    private Variable<String> selectUnassignedVariable(CSP csp) {
        return (Variable<String>) csp.getVariables().stream()
                .filter(v -> !v.isAssigned())
                .findFirst()
                .orElse(null);
    }
}

