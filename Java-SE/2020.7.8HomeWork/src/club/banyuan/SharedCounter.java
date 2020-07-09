package club.banyuan;

import java.util.concurrent.*;

public class SharedCounter {
    static int counter = 0;
    static final byte[] lock = new byte[0];
    static boolean flag = false;

    public static void reset() {
        counter = 0;
    }

    public static int increment(int numThreads, int numIncrementsPerThread) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < numThreads; i++) {
            Future<Integer> future = threadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    synchronized (SharedCounter.class) {
                        return counter += numIncrementsPerThread;
                    }
                }
            });

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    counter += numIncrementsPerThread;
//                }
//            }).start();
//            thread.join();
//            Thread.sleep(500);
        }
        threadPool.shutdownNow();
        threadPool.awaitTermination(200, TimeUnit.MILLISECONDS);
        return counter;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(increment(1, 10));

    }
}
