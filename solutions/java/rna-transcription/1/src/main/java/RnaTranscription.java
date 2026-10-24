import java.util.Map;

class RnaTranscription {
    private static final Map<Character,Character> DNA_TO_RNA = Map.of('A', 'U', 'C', 'G', 'G', 'C', 'T', 'A');

    
    String transcribe(String dnaStrand) {
        StringBuilder rnaBuilder = new StringBuilder();
        char[] chArray = dnaStrand.toCharArray();
        for (char nucleotidCh: chArray) {
            rnaBuilder.append(DNA_TO_RNA.get(nucleotidCh));
        }
        return rnaBuilder.toString();
    }

}
