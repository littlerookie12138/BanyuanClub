package club.banyuan;

import javax.xml.crypto.Data;

public class DataSet implements Measurer, Measurable{
  // TODO: 定义private 实例变量
  private double sum;
  private int count;
  private Object maximum;
  private Measurer measurer;
  private Object[] countries = new Object[4];

  public DataSet() {

  }

  public DataSet(Measurer aMeasurer) {
    sum = 0;
    count = 0;
    maximum = null;
    measurer = aMeasurer;
  }

  @Override
  public double measure(Object anObject) {

    return ((Country)anObject.);
  }

  // TODO： 重载方法，使得Contry统计 DataSetTester 运行成功
  public void add(Object x) {
    countries[count] = x;
    sum = sum + measurer.measure(x);
    if (count == 0 || measurer.measure(maximum) < measurer.measure(x)) {
      maximum = x;
    }
    count++;
  }

  public double getAverage() {
    // TODO: check divide by zero. compute the average value.
  }

  public Object getMaximum() {
    for (Object country : countries) {
      if (((Country)country).getMeasure() > ) {

      }
    }
    return maximum;
  }


  @Override
  public double getMeasure() {

    return 0;
  }
}