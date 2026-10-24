import java.util.ArrayList;
import java.util.List;

class ProteinTranslator {
    private final List<String> acidList = new ArrayList<>();
    
    List<String> translate(String rnaSequence) {
        for (int i=0; i<rnaSequence.length(); i+= 3) {
            switch (rnaSequence.substring(i, Math.min(i+3, rnaSequence.length()))) {
                case "UAA":
                case "UAG":
                case "UGA":
                    return acidList;
                case "AUG":
                    acidList.add("Methionine");
                    break;
                case "UUU":
                case "UUC":
                    acidList.add("Phenylalanine");
                    break;
                case "UUA":
                case "UUG":
                    acidList.add("Leucine");
                    break;
                case "UCU":
                case "UCC":
                case "UCA":
                case "UCG":
                    acidList.add("Serine");
                    break;
                case "UAU":
                case "UAC":
                    acidList.add("Tyrosine");
                    break;
                case "UGU":
                case "UGC":
                    acidList.add("Cysteine");
                    break;
                case "UGG":
                    acidList.add("Tryptophan");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid codon");
            }
        }
        return acidList;
    }
}
