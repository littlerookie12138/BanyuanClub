package club.banyuan;

public class Main {
    public static void main(String[] args) {
        WeekDay sat = WeekDay.SATURDAY;
        for (WeekDay value : WeekDay.values()) {
            System.out.println(value.toString() + (value.isWeekDay() ? "是工作日" : "是休息日"));
            System.out.println(value.toString() + "和周六比的值为" + value.compareTo(sat));
            System.out.println("-----------------------");
        }

    }
}
