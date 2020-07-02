package dataSet;

public class RectangleMeasurer<T> implements Measurer<T> {

  @Override
  public double measure(T anObject) {
    if (anObject instanceof Rectangle) {
      Rectangle rectangle = (Rectangle) anObject;
      return rectangle.width * rectangle.height;
    }
    throw new IllegalArgumentException("不是矩形类");
  }
}
