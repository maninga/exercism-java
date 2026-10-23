class Badge {
    public String print(Integer id, String name, String department) {
        String badge = "";
        badge += (id != null) ? ("[" + id + "] - ") : "";
        badge +=  name + " - ";
        badge += (department != null) ? department.toUpperCase() : "OWNER";

        return badge;
    }
}
