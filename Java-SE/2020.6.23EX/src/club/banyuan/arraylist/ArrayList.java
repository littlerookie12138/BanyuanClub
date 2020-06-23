package club.banyuan.arraylist;

public class ArrayList {

    private Object[] value;
    private Object[] tempValue;
    private int index;

    public ArrayList(int capacity) {
        value = new Object[capacity];
    }

    public void increaseCapacity(int newCapacity) {
        Object [] newValue = new Object[newCapacity];
        // copy value to new value
        System.arraycopy(value, 0, newValue, 0, value.length);
        value = newValue;
    }

    public ArrayList() {
        this(10);
    }

    /**
     * 更新数组指定下标的元素
     *
     * @param index   待更新的元素下标
     * @param element 更新后的元素对象
     * @return 被替换掉的旧对象
     */
    public Object set(int index, Object element) {
        if (index < 0 || index > value.length) {
            return null;
        }
        Object temp = null;
        temp = value[index];
        value[index] = element;

        return temp;

    }

    /**
     * 查询指定位置下标的元素
     *
     * @param index 需要判断index是否合法
     * @return 返回查找到的元素，找不到返回null
     */
    public Object get(int index) {
        if (index < 0 || index > value.length) {
            return null;
        }
        return value[index];
    }

    /**
     * 清空集合内容
     */
    public void clear() {
        value = new Object[0];
    }

    /**
     * 删除指定下标的元素
     *
     * @param index 元素下标
     * @return 将删除的元素返回，如果下标不合理，返回null
     */
    public Object remove(int index) {
        if (index < 0 || index > value.length) {
            return null;
        }
        Object temp = null;
        for (int i = index; i < value.length; i++) {
            temp = value[i];
            value[i] = value[i + 1];
        }
        value[value.length - 1] = null;

        return temp;
    }

    /**
     * 查找并删除元素
     *
     * @param o 通过目标元素 equals 方法判断是否匹配，
     *          需要判断o是否为null，如果传入null，则用== 进行比较
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < value.length; i++) {
                if (value[i] == o) {
                    while (i < value.length) {
                        value[i] = value[i + 1];
                    }
                    value[value.length - 1] = null;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < value.length; i++) {
                if (value[i].equals(o)) {
                    while (i < value.length) {
                        value[i] = value[i + 1];
                    }
                    value[value.length - 1] = null;

                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }


    /**
     * 添加一个元素
     *
     * @param o
     * @return 添加成功后返回true 失败返回false
     */
    public boolean add(Object o) {

        if (index + 1 >= value.length) {
            increaseCapacity(value.length * 2);
            value[index++] = o;
            System.out.println("Value加长后的长度 " + value.length);
            return true;
        } else {
            value[index++] = o;
            return true;
        }
    }

    /**
     * @return 数组中没有元素，返回true
     */
    public boolean isEmpty() {
        for (int i = 0; i < value.length; i++) {
            if (value[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回数组中保存的元素个数
     *
     * @return
     */
    public int size() {
        int index = 0;
        while (value[index] != null) {
            index++;
        }

        return index;
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);

        arrayList.add(2);

        arrayList.add(3);

        arrayList.add(4);

        arrayList.add(5);

        arrayList.add(6);

        arrayList.add(7);

        arrayList.add(8);

        arrayList.add(9);

        arrayList.add(10);

        arrayList.add(11);

        arrayList.add(12);

        arrayList.add(13);

        arrayList.add(14);

        arrayList.add(15);

        arrayList.add(16);

        arrayList.add(17);

        arrayList.add(18);

        arrayList.remove(19);

        for (Object o : arrayList.value) {
            if (o != null) {
                System.out.println(o);
            }
        }

        Object o = arrayList.get(1);
        int a = (int) o;
    }
}