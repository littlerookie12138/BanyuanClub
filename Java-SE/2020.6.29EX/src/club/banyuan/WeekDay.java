package club.banyuan;

public enum WeekDay {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日"),

    ;

    private final String dayInChinese;

    WeekDay(String dayInChinese) {
        this.dayInChinese = dayInChinese;
    }

    public boolean isHoliday() {

        if (this.name().equals(SATURDAY.name()) || this.name().equals(SUNDAY.name())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWeekDay() {
        if (this.name().equals(SATURDAY.name()) || this.name().equals(SUNDAY.name())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return  dayInChinese;
    }
}
