import java.util.Scanner;

class draw {
    /*
    出现的概率

    |大吉 | 中吉 | 小吉 | 吉 | 末吉 | 凶  | 大凶 |
    | ----|----|----|----|----|----|---- |
    | 5% | 10% | 15% | 30% | 20% | 15% | 5%|

    抽签程序，输入任意内容，抽一张，展示内容

    输入0退出
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("exit")) {


            //随机数默认属于系统时间的毫秒数
            double probability = Math.random();
            if (probability < 0.05) {
                System.out.println("大吉");
            } else if (0.05 <= probability && probability < 0.15) {
                System.out.println("中吉");
            } else if (0.15 <= probability && probability < 0.3) {
                System.out.println("小吉");
            } else if (0.3 <= probability && probability < 0.6) {
                System.out.println("吉");
            } else if (0.6 <= probability && probability < 0.8) {
                System.out.println("末吉");
            } else if (0.8 <= probability && probability < 0.95) {
                System.out.println("凶");
            } else {
                System.out.println("大凶");
            }
            input = sc.nextLine();
        }
    }

}