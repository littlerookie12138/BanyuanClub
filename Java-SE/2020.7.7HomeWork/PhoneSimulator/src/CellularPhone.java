import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CellularPhone extends Thread {

    private volatile boolean callInProgress = false;
    private volatile boolean keepGoing = true;
    private Lock lock = new ReentrantLock();

//  private static byte[] lock = new byte[0];//使用0长度的byte数组比Object长度编译所需的字节码都更少
//  //byte数组3字节 Object7字节


    public CellularPhone(String name) {
        super(name);
    }

    /**
     * 假设控制台是手机的窗口。
     *
     * @param message 要显示的消息
     */
    void display(String message) {
        System.out.println(message);
    }

    /**
     * 接听电话。在终端显示消息。
     *
     * @param name               调用方的名称
     * @param callDisplayMessage 呼叫正在进行时显示的消息
     * @returns 如果调用被接受，则为true
     */
    public boolean startCall(String name, String callDisplayMessage) throws InterruptedException {
        lock.lock();
        callInProgress = true;
        display("<" + name + ">: Call (" + callDisplayMessage + ") begins");
        return true;
    }

    /**
     * 挂断电话。在此方法完成后，电话能够接听其他电话。
     *
     * @param name               调用方的名称
     * @param callDisplayMessage 要显示的消息
     */
    public void endCall(String name, String callDisplayMessage) throws InterruptedException {
        display("   <" + name + ">: Call (" + callDisplayMessage + ") ends");
        callInProgress = false;
        lock.unlock();
    }

    /**
     * 显示手机正在等待做某事
     */
    synchronized private void displayWaiting() {
        display("<" + this.getName() + ">: Waiting...");
    }

    /**
     * 停止电话接听程序
     */
    public void stopPhone() {
        keepGoing = false;
    }

    /**
     * 运行模拟器
     */
    public void run() {
        // 循环直到stopPhone被调用
        while (keepGoing) {
            // 如果没有电话
            if (!callInProgress) {
                displayWaiting();

                // 假装手机在做别的事情
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
