package club.banyuan.circleQueue;

public class CircleQueue implements Queue {
    private Object[] circleQueue = new Object[10];
    private int insertIndex;//要插入的位置
    private int outIndex;//要出队的位置

    public void increaseCapacity(int newCapacity) {
        Object [] newQueue = new Object[newCapacity];
        // copy value to new value
        System.arraycopy(circleQueue, 0, newQueue, 0, circleQueue.length);
        circleQueue = newQueue;
    }

    @Override
    public void add(Object o) {
        if (insertIndex != outIndex) {
            //不需要扩容的情况
            if (insertIndex + 1 == circleQueue.length) {
                circleQueue[insertIndex] = o;
                if (circleQueue[0] == null) {
                    insertIndex = 0;
                    return;
                } else {
                    //需要扩容
                    increaseCapacity(circleQueue.length * 2);
                    insertIndex++;
                    return;
                }
            } else {
                circleQueue[insertIndex++] = o;
                return;
            }
        } else if (circleQueue[outIndex] != null){
            //需要扩容的情况
            increaseCapacity(circleQueue.length * 2);
            circleQueue[insertIndex++] = o;
            return;
        } else {
            circleQueue[insertIndex++] = o;
            return;
        }
    }


    @Override
    public void delete() {
        if (isEmpty()) {
            System.out.println("队列为空，无法删除");
            return;
        } else {
            if (outIndex == circleQueue.length - 1) {
                Object temp = circleQueue[outIndex];
                circleQueue[outIndex] = null;
                outIndex = 0;
                return;
            } else {
                Object temp = circleQueue[outIndex];
                circleQueue[outIndex++] = null;
                return;
            }
        }
    }

    public int size() {
        int index = 0;
        for (Object o : circleQueue) {
            if (o != null) {
                index++;
            }
        }

        return index;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        CircleQueue circleQueue = new CircleQueue();
        circleQueue.add(0);
        circleQueue.add(1);
        circleQueue.add(2);
        circleQueue.add(3);
        circleQueue.add(4);
        circleQueue.add(5);
        circleQueue.add(6);
        circleQueue.add(7);
        circleQueue.add(8);
        circleQueue.add(9);
        circleQueue.add(10);
        circleQueue.add(11);
        circleQueue.add(12);
        circleQueue.add(13);

        for (Object o : circleQueue.circleQueue) {
            if (o != null) {
                System.out.println(o);
            }
        }

        circleQueue.delete();
        circleQueue.delete();
        circleQueue.delete();
        circleQueue.delete();

        circleQueue.add(14);
        circleQueue.add(15);
        circleQueue.add(16);
        circleQueue.add(17);
        circleQueue.add(18);
        circleQueue.add(19);
        circleQueue.add(20);

        for (Object o : circleQueue.circleQueue) {
            if (o != null) {
                System.out.println(o);
            }
        }
    }
}
