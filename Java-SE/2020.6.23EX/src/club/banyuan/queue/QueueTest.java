package club.banyuan.queue;

import club.banyuan.circleQueue.Queue;

public class QueueTest implements Queue {
    private Object[] testQueue = new Object[10];
    private int insertIndex;//循环队列需要一个下标标记下一个要存放的位置
    private int outIndex;//队列出队的位置
    private int index;

    public void increaseCapacity(int newCapacity) {
        Object [] newQueue = new Object[newCapacity];
        // copy value to new value
        System.arraycopy(testQueue, 0, newQueue, 0, testQueue.length);
        testQueue = newQueue;
    }

    @Override
    public void add(Object o) {
        if (index >= testQueue.length) {
            increaseCapacity(testQueue.length + 1);
            testQueue[index++] = o;
            return;
        } else {
            testQueue[index++] = o;
            return;
        }
    }


    public void delete() {
        Object temp = null;
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        } else {
            temp = testQueue[0];
            for (int i = 1; i < testQueue.length; i++) {
                testQueue[i - 1] = testQueue[i];
            }
        }

        index--;
    }

    @Override
    public boolean isEmpty() {
        if (testQueue[0] == null) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        QueueTest queueOne = new QueueTest();
        System.out.println(queueOne.isEmpty() ? "空的" : "不是空的");

        queueOne.add(1);
        queueOne.add(2);
        queueOne.add(3);
        queueOne.add(4);
        queueOne.add(5);
        queueOne.add(6);
        queueOne.add(7);
        queueOne.add(8);
        queueOne.add(9);
        queueOne.add(10);
        queueOne.add(11);
        queueOne.add(12);
        System.out.println(queueOne.testQueue.length);
        for (Object o : queueOne.testQueue) {
            if (o != null) {
                System.out.println(o.toString());
            }
        }


        System.out.println("---------------------------");
//        System.out.println(queueOne.delete().toString());
        System.out.println("---------------------------");
        for (Object o : queueOne.testQueue) {
            if (o != null) {
                System.out.println(o.toString());
            }
        }
    }
}
