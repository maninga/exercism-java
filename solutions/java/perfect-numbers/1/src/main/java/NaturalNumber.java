class NaturalNumber {
    private final Classification classification;

    NaturalNumber(int number) {
        int aliquoteSum = 0;

        if (number <= 0)
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        if (number > 1)
            aliquoteSum += 1;

        // On itère jusqu'à la racine carrée de n
        // (i * i <= number est plus rapide en calcul que i <= Math.sqrt(number))
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) { 
                aliquoteSum += i; // On ajoute le premier diviseur de la paire
                
                // On ajoute le deuxième diviseur (le quotient)
                // Exemple : si n=36 et i=2, on ajoute 36/2 = 18
                if (i != number / i) {
                    aliquoteSum += number / i; // Évite de rajouter deux fois 6 dans le cas de 6x6
                }
            }
        }
        switch (Integer.compare(number, aliquoteSum)) {
            case -1:
                classification = Classification.ABUNDANT;
                break;
            case 0:
                classification = Classification.PERFECT;
                break;
            default:
                classification = Classification.DEFICIENT;
                break;
        }
    }

    Classification getClassification() {
        return classification;
    }
}
