import java.text.Normalizer;

class MicroBlog {
    private final int maxLength;
    private final boolean normalize;

    public MicroBlog() {
        this.maxLength = 5;
        this.normalize = true;
    }

    public MicroBlog(int maxLength, boolean normalize) {
        if (maxLength < 0) {
            throw new IllegalArgumentException("maxLength should be positive.");
        }
        this.maxLength = maxLength;
        this.normalize = normalize;
    }
    
    public String truncate(String input) {
        if (input == null || input.isEmpty() || maxLength == 0) {
            return "";
        }

        // 1. Normalisation optionnelle
        String processedInput = normalize 
            ? Normalizer.normalize(input, Normalizer.Form.NFC) 
            : input;

        // 2. Troncature basée sur le flux de points de code
        return processedInput.codePoints()
                .limit(maxLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
