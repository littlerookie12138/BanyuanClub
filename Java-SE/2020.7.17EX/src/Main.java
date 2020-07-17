public class Main {
    public static void main(String[] args) {
        System.out.println(validateQQ("23a21515"));
        System.out.println(validateQQ("03121515"));
        System.out.println(validateQQ("2321515123512512"));
        System.out.println(validateQQ("23a2"));
        System.out.println(validateQQ("9999999999"));
    }

    public static boolean validateQQ(String qq) {

        if (qq.length() < 7 || qq.length() > 10) {
            return false;
        }

        if (qq.startsWith("0")) {
            return false;
        }

        try {
            Long.parseLong(qq);
        } catch (Exception e) {
            return false;
        }


        return true;
    }
}
