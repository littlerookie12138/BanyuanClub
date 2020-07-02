import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pairs<K, V> implements Iterable<Pair<K, V>> {

  /* 声明一对对象的固定大小的数组（最多10个元素） */
  private int index;
  private K first;
  private V second;

  /**
   * 创建一个集合，该集合将存储成对添加的项目。
   */
  private List<Pair<K, V>> pairs = new ArrayList<>(10);

  public Pairs() {

  }

  public List<Pair<K,V>> getPairs() {
    return pairs;
  }
  /**
   * 创建一个新的对，并在有空间的情况下将其添加到集合中。
   *
   * @param first  The first object.
   * @param second The second object.
   */
  public boolean addPair(K first, V second) {
//    if (pairs.size() < 10) {
      return pairs.add(new Pair<K, V>(first, second));
//    } else {
//      throw new RuntimeException("列表满了");
//    }
  }


  @Override
  public Iterator<Pair<K, V>> iterator() {
    return new PairIterator();
  }

  /*
   * 根据以下位置的API文档在此处实现迭代器
   * https://docs.oracle.com/javase/10/docs/api/java/util/Iterator.html
   * 按照规定抛出异常
   */
  private class PairIterator implements Iterator<Pair<K, V>> {
    private List<Pair<K, V>> pairs= new ArrayList<>(Pairs.this.pairs);
    private int next;
    private Pair<K, V> cur;

    @Override
    public boolean hasNext() {
     return pairs.size() > next;
    }

    /**
     * 返回迭代器中的下一个对象
     */
    @Override
    public Pair<K, V> next() {
      if (hasNext()) {
        cur = pairs.get(next++);
        return cur;
      } else {
        throw new RuntimeException("没有下一个元素了");
      }
    }

    /**
     * 从集合中移除next（）返回的上一个对象。
     */
    @Override
    public void remove() {
      if (cur != null) {
        Pairs.this.pairs.remove(cur);
        cur = null;
      }
    }
  }

}