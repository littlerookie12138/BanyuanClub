public enum Season {
    SPRING("春天"),
    SUMMER("夏天"),
    FALL("秋天"),
    WINTER("冬天"),

    ;
    private String seasonInChinese;

    Season(String seasonInChinese) {
        this.seasonInChinese = seasonInChinese;
    }

    public void getMonthRange() {
        switch (this) {
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

}
