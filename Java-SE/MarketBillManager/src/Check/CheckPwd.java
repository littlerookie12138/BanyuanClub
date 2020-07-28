package Check;

public class CheckPwd {
    public static boolean check(String pwd) {
        String regex = "\\w{6,15}";

        return pwd.matches(regex);
    }
}
