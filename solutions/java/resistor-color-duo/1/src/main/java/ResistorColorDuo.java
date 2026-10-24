import java.util.Arrays;

class ResistorColorDuo {

    private final String[] colorArray = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    int colorCode(String color) {
        return Arrays.asList(colorArray).indexOf(color);
    }

    String[] colors() {
        return colorArray;
    }

    int value(String[] colors) {
        return colorCode(colors[0]) * 10 + colorCode(colors[1]);
    }
}
