package Check;

public class CheckPhone {

    public static boolean check(String phone) {
        String regex = "[1][358]\\d{9}";

        return phone.matches(regex);
    }


}
