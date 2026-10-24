import java.util.Arrays;
class ResistorColorTrio {
    private final String[] colorArray = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
    private final String[] prefixArray = {" ", "0 ", "00 ", " kilo", "0 kilo", "00 kilo", " mega", "0 mega", "00 mega", " giga"};

    int colorCode(String color) {
        return Arrays.asList(colorArray).indexOf(color);
    }
    int colorPrefix(String color) {
        return Arrays.asList(colorArray).indexOf(color);
    }

    String[] colors() {
        return colorArray;
    }

    int value(String[] colors) {
        return colorCode(colors[0]) * 10 + colorCode(colors[1]);
    }

    int prefixIndex(String[] colors) {
        return colorPrefix(colors[2]);
    }

    String prefixStr(int index) {
        return prefixArray[index];
    }

    String label(String[] colors) {
        int resistance = value(colors);
        int metricIndex = prefixIndex(colors);
        String metricPrefix = prefixStr(metricIndex);
        while (resistance > 0 && resistance % 10 == 0) {
            metricIndex++;
            metricPrefix = prefixStr(metricIndex);
            resistance /= 10;
        }
        
        return resistance + metricPrefix + "ohms";
    }
}

