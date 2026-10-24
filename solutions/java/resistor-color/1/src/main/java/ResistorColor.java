import java.util.Arrays;

class ResistorColor {
    private final String[] colorArray = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    int colorCode(String color) {
        return Arrays.asList(colorArray).indexOf(color);
    }

    String[] colors() {
        return colorArray;
    }
}
