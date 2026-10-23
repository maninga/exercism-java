public class Twofer {
    public String twofer(String name) {
        return "One for " + ((name != null && name != "") ? name : "you") + ", one for me.";
    }
}
