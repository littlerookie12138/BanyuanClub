public class Driver {

  public static void main(String[] args) {
    // 创建一个电话
    CellularPhone aPhone = new CellularPhone("Phone");
    aPhone.start();



    // 创建一个消息生成器
    MessageGenerator aPhoneMessageMather = new MessageGenerator("Mother", aPhone);
    MessageGenerator aPhoneMessageFather = new MessageGenerator("Father", aPhone);
    aPhoneMessageMather.start();
    aPhoneMessageFather.start();

    // 创建两个呼叫生成器，他们坚持不断地给电话打电话。
    PhoneCallGenerator pcGen1 = new PhoneCallGenerator("Tom", aPhone);
    PhoneCallGenerator pcGen2 = new PhoneCallGenerator("Jerry", aPhone);
    pcGen1.start();
    pcGen2.start();

    try {
      // 等待，直到所有的电话都结束
      pcGen1.join();
      pcGen2.join();
      // 停止电话
      aPhone.stopPhone();
    } catch (InterruptedException ie) {
      // 必须要处理的join抛出的异常
      System.exit(1);
    }
  }
}
