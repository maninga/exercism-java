import java.util.ArrayList;
import java.util.List;

public class LanguageList {
    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
        return this.count() == 0;
    }

    public void addLanguage(String language) {
        if (!this.containsLanguage(language))
            this.languages.add(language);
    }

    public void removeLanguage(String language) {
        if (this.containsLanguage(language))
            this.languages.remove(language);
    }

    public String firstLanguage() {
        return this.languages.get(0);
    }

    public int count() {
        return this.languages.size();
    }

    public boolean containsLanguage(String language) {
        return this.languages.contains(language);
    }

    public boolean isExciting() {
        return this.containsLanguage("Java") || this.containsLanguage("Kotlin");
    }
}
