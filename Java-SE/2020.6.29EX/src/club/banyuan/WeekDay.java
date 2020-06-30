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
    private boolean isRigth = false;

    WeekDay(String dayInChinese) {
        this.dayInChinese = dayInChinese;
    }

    public boolean isHoliday() {
        if (isRight()) return false;

        if (this.name().equals(SATURDAY.name()) || this.name().equals(SUNDAY.name())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRight() {
        for (WeekDay value : WeekDay.values()) {
            if (this.name().equals(value.name())) {
                isRigth = true;
            }
        }
        if (!isRigth) {
            System.out.println("查询的枚举类型不合法！");
            return true;
        }
        return false;
    }

    public boolean isWeekDay() {

        if (isRight()) {
            return false;
        }

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
