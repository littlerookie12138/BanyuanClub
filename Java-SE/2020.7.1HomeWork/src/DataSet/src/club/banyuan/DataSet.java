package DataSet.src.club.banyuan;

public class DataSet<T> implements Measurer{

  private double sum;
  private int count;
  private T maximum;
  private T measurer;

  public DataSet() {
  }

  public DataSet(T aMeasurer) {
    sum = 0;
    count = 0;
    maximum = null;
    measurer = aMeasurer;
  }

  public void add(T x) {
    if (x instanceof Measurable) {
      add((Measurable) x);
    } else {
      addByMeasurer((T) x);
    }
  }

  private void addByMeasurer(T x) {
    sum = sum + measurer.measure(x);
    if (count == 0 || measurer.measure(maximum) < measurer.measure(x)) {
      maximum = x;
    }
    count++;
  }

  private void add(Measurable measurable) {
    sum = sum + measurable.getMeasure();
    if (count == 0 || ((Measurable) maximum).getMeasure() < measurable.getMeasure()) {
      maximum = measurable;
    }
    count++;
  }

  public double getAverage() {
    if (count == 0) {
      return 0;
    }
    return sum / count;
  }

  public T getMaximum() {
    return maximum;
  }


  @Override
  public double measure(Object anObject) {
    return 0;
  }
}