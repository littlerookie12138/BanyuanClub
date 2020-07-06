public class Main {
    public static void main(String[] args) {
        Thread myThread1 = new MyThread();
        myThread1.start();
        Thread myThread2 = new MyThread();
        myThread2.start();
    }
}
