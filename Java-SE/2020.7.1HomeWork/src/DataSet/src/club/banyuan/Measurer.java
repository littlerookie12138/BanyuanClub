package DataSet.src.club.banyuan;

public interface Measurer<T> {

  /**
   * 计算对象的数量.
   */
  double measure(Object anObject);
}