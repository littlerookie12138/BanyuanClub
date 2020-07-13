import java.io.IOException;
import java.util.Scanner;

public class VideoStoreLauncher {
    private static final Scanner scanner = new Scanner(System.in);
    private static final VideoStore vs = new VideoStore();

    public static void main(String[] args) throws Exception {
        vs.load();

        while (true) {
            System.out.println("1. 添加电影");
            System.out.println("2. 出租电影");
            System.out.println("3. 归还电影");
            System.out.println("4. 查询电影");
            System.out.println("0. 退出");

            String input = scanner.next();
            switch (input) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    checkoutMovie();
                    break;
                case "3":
                    returnMovie();
                    break;
                case "4":
                    printInfo();
                    break;
                case "0":
                    System.out.println("谢谢使用！");
                    return;
            }


        }
    }

    private static void returnMovie() throws IOException {
        System.out.println("请输入您要归还的电影并打分");
        String name = scanner.next();
        int score = scanner.nextInt();
        vs.returnAndScore(name, score);
    }

    private static void checkoutMovie() throws IOException {
        System.out.println("请输入您想租的电影名字:");
        String name = scanner.next();
        vs.checkOut(name);
    }

    private static void printInfo() {
        vs.printVideoList();
    }

    private static void addMovie() throws IOException {
        System.out.println("请输入电影名字：");
        String name = scanner.next();
        // Video video = new Video("name");
        Video video = vs.addVideo(name, "科幻");
    }

}
