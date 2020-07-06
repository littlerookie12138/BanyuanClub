package club.banyuan;

public class ReverseHelloMultithreaded {

    public static void doReverseHello() throws InterruptedException {

        for (int i = 50; i > 0; i--) {
            Thread thread = new Thread() {
                public void run() {
                    Thread thread = new Thread();
                    System.out.print("Hello from thread ");
                }
            };
            thread.start();
            Thread.sleep(50);
            System.out.println(i);
        }
    }
}
