package club.banyuan.time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 比较在基于数组的列表和基于链表的列表的末尾增加n个值所需的时间
 * <p>
 * 1.创建一个计时器。
 * <p>
 * 2.计算将n个值添加到空链表的时间：
 * a.创建一个空的链表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i添加到list中
 * d.停止计时器
 * e.显示时间
 * <p>
 * 3.计算将n个值添加到空数组列表的时间：
 * a.创建一个空的数组列表
 * b.启动计时器
 * c.依次将0..n-1中的每个值i添加到list中
 * d.停止计时器
 * e.显示时间
 */
public class Append implements Timer{

  private static long timeStart;
  private static long timeEnd;
  private boolean isStart;

  public static void main(String args[]) {
    Append time = new Append();
    List<Integer> list = new ArrayList<>();
    List<Integer> linkedList = new LinkedList<>();
    for (int i = 0; i < 1000000; i++) {
      if (i == 0) {
        time.start();
      }
      list.add(i);
    }
    time.stop();
    long timeMillisecond = time.getTimeMillisecond();
    time.reset();


    for (int i = 0; i < 1000000; i++) {
      if (i == 0) {
        time.start();
      }
      linkedList.add(i);
    }
    time.stop();
    long timeMillisecond1 = time.getTimeMillisecond();
    time.reset();
    System.out.println("链表存储时间 - 数组存储时间 = " + (timeMillisecond1 - timeMillisecond));
  }

  @Override
  public void start() throws IllegalStateException {
    if (isStart) {
      throw new IllegalStateException("上一次计时未停止！");
    }
    timeStart = System.currentTimeMillis();
    isStart = true;
  }

  @Override
  public void stop() throws IllegalStateException {
    if (!isStart) {
      throw new IllegalStateException("尚未开始计时无法调用停止计时！");
    }
    timeEnd = System.currentTimeMillis();
    isStart = false;
  }

  @Override
  public void reset() {
    if (!isStart) {
      timeStart = 0L;
      timeEnd = 0L;
    } else {
      throw new IllegalStateException("未停止或未开始无法重置");
    }
  }

  @Override
  public long getTimeMillisecond() {
    return timeEnd - timeStart;
  }
}

