import java.util.*;

public class ShiftOne {
    public static void main(String[] args) {
        String s = "1234567";
        s = shiftOne(s, 2);
        System.out.println(s);
    }

    public static String shiftOne(String target, int num) {
        char[] char1 = target.toCharArray();
        char temp = '0';
        for (int i = 0; i < char1.length; i++) {
            if (i == num) {
                temp = char1[num];
                do {
                    char1[i] = char1[i + 1];
                    i++;
                } while (i != char1.length - 1);
            }
        }
        System.out.println(temp);
        char1[char1.length - 1] = temp;
        String str = "";
        for (char a : char1) {
            str += (a + "");
        }


        return str;
    }
}