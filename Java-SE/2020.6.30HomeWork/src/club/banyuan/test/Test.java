package club.banyuan.test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    private static String result = "";

    public static void main(String[] args) {
        test(1);
        result += "*";
        test(0);
        System.out.println(result);
    }

    public static void test(int i) {
        result += "1";
        try {
            if (i == 0) {
                throw new RuntimeException("");
            }
            result += "2";
        } catch (Exception e) {
            result += "3";
            return;
        } finally {
            result += "4";
        }
        result += "5";
    }

}

