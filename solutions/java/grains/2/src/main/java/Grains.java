import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64)
            throw new IllegalArgumentException("square must be between 1 and 64");
        return BigInteger.valueOf(2).pow(square - 1);
    }

    BigInteger grainsOnBoard() {
        // La somme des puissances de 2 de \(0\) à \(n\) est égale à \(2^{n+1} - 1\).
        // Par exemple :
        // • Pour \(n = 3\) : \(2^0 + 2^1 + 2^2 + 2^3 = 1 + 2 + 4 + 8 = 15\)
        // • Avec la formule : \(2^{3+1} - 1 = 2^4 - 1 = 16 - 1 = 15\)
        int boardSquares = 64;
        // return BigInteger.ONE.shiftLeft(n + 1).subtract(BigInteger.ONE);
        return BigInteger.valueOf(2).pow(boardSquares).subtract(BigInteger.valueOf(1));
    }

}
