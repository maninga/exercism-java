import java.util.HashMap;
import java.util.Map;

public class DialingCodes {

    private final Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return this.codes;
    }

    public void setDialingCode(Integer code, String country) {
        this.getCodes().put(code, country);
    }

    public void unsetDialingCode(Integer code) {
        this.getCodes().remove(code);
    }

    public String getCountry(Integer code) {
        return this.getCodes().get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!(this.getCodes().containsKey(code) || this.getCodes().containsValue(country)))
            this.setDialingCode(code, country);
    }

    public Integer findDialingCode(String country) {
        for (Map.Entry<Integer, String> entry : this.getCodes().entrySet())
            if (entry.getValue().equals(country))
                return entry.getKey();
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
        Integer currentCode = this.findDialingCode(country);
        if (currentCode != null && currentCode != code) {
            this.unsetDialingCode(currentCode);
            this.setDialingCode(code, country);
        }
                    
    }
}
