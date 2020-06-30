public class Main {
    static void getMonthRange(Season season){
        switch (season) {
            case SPRING:
                System.out.println(3 + "~" + 5);
                break;
            case SUMMER:
                System.out.println(6 + "~" + 8);
                break;
            case FALL:
                System.out.println(9 + "~" + 11);
                break;
            case WINTER:
                System.out.println(12 + "~" +2);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Season season = Season.WINTER;
        getMonthRange(season);
        season.getMonthRange();
    }
}
