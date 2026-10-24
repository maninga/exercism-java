import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MicroBlog {
    private final int maxLength;
    private final boolean normalize;
    private static final Pattern GRAPHEME_PATTERN = Pattern.compile("\\X");

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

        // 2. Troncature basée sur la capture des clusters de graphèmes
        Matcher matcher = GRAPHEME_PATTERN.matcher(processedInput);
        StringBuilder result = new StringBuilder();
        int count = 0;

        while (matcher.find() && count < maxLength) {
            result.append(matcher.group());
            count++;
        }

        return result.toString();
        }
}
