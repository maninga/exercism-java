class Badge {
    public String print(Integer id, String name, String department) {
        String badge = "";
        badge += (id != null) ? String.format("[%d] - ", id) : "";
        badge +=  String.format("%s - ", name);
        badge += (department != null) ? department.toUpperCase() : "OWNER";

        return badge;
    }
}
