import java.io.Console;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!!");

//        Scanner in = new Scanner(System.in);
//        String name = in.next();//遇到空格停止
//        String namee = in.nextLine();//读取一整行
//        int age = in.nextInt();//读取一个整数

        /*
        * Console类隐藏输入
        */
        Console cons =System.console();
        String username = cons.readLine("Username: ");
        char pass[] = cons.readPassword("Password: ");


//        System.out.println(name);
        System.out.println(username);
        System.out.println(pass);
    }
}
