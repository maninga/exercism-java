public class Hamming {
    private final String left;
    private final String right;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand == null || rightStrand == null)
            throw new IllegalArgumentException("strands must not be null");
        if (leftStrand.length() != rightStrand.length())
            throw new IllegalArgumentException("strands must be of equal length");
        left = leftStrand;
        right = rightStrand;
    }

    public int getHammingDistance() {
        int length = left.length();
        int diff = 0;
        for (int i = 0; i < length; i++) {
            if (left.substring(i, length) == right.substring(i, length))
                return diff;
            if (left.charAt(i) != right.charAt(i))
                diff++;
        }
        return diff;
        
    }
}
