package club.banyuan;

public class MaxMultithreaded {


  /**
   * 计算数组元素的sin值之后，返回最大值。
   *
   * @param arr 目标数组
   * @return sin(数组元素)的最大值
   * @throws InterruptedException 不应该出现此异常
   */
  public static double max(int[] arr, int numThreads) throws InterruptedException {
    int len = arr.length;
    double ans = 0;

    // 创建并启动线程。
    SumThread[] ts = new SumThread[numThreads];
    for (int i = 0; i < numThreads; i++) {
      ts[i] = new SumThread(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
      ts[i].start();
    }

    // 等待线程完成并计算它们的结果。
    for (int i = 0; i < numThreads; i++) {
      ts[i].join();
      if (ans < ts[i].getAns()) {
        ans = ts[i].getAns();
      }
    }
    return ans;
  }
}
