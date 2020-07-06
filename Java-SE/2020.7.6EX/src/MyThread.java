import java.util.Scanner;

public class MyThread extends Thread {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            char[] chars = str.toCharArray();
            try {
                for (int i = 0; i < chars.length; i++) {
                    System.out.print(Thread.currentThread().getName() + chars[i]);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
