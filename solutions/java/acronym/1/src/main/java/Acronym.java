class Acronym {
    private final String acronym;

    Acronym(String phrase) {
        StringBuilder acronymBuilder = new StringBuilder();

        for (String word : phrase.replaceAll("\\s+", "-").replaceAll("[^a-zA-Z\\-]","").toUpperCase().split("-"))
            if (word != null && !word.isEmpty())
                acronymBuilder.append(word.charAt(0));

        acronym = acronymBuilder.toString();
    }

    String get() {
        return acronym;
    }

}
