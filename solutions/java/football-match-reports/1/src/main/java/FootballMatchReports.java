public class FootballMatchReports {    
    public static String onField(int shirtNum) {
        String field;
        switch (shirtNum) {
            case 1:
                field = "goalie";
                break;
            case 2:
                field = "left back";
                break;
            case 3:
            case 4:
                field = "center back";
                break;
            case 5:
                field = "right back";
                break;
            case 6:
            case 7:
            case 8:
                field = "midfielder";
                break;
            case 9:
                field = "left wing";
                break;
            case 10:
                field = "striker";
                break;
            case 11:
                field = "right wing";
                break;
            default:
                field = "invalid";
                break;
        }
        return field;
    }
}
