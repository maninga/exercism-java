public class Hamming {
    private final int hammingDistance;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand == null || rightStrand == null)
            throw new IllegalArgumentException("strands must not be null");
        if (leftStrand.length() != rightStrand.length())
            throw new IllegalArgumentException("strands must be of equal length");
        int length = leftStrand.length();
        int diff = 0;
        for (int i = 0; i < length; i++) {
            if (leftStrand.substring(i, length) == rightStrand.substring(i, length))
                break;
            if (leftStrand.charAt(i) != rightStrand.charAt(i))
                diff++;
        }
        hammingDistance = diff;
    }

    public int getHammingDistance() {
        return hammingDistance;
        
    }
}
