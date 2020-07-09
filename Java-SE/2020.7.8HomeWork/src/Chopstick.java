import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private boolean isToken;

    public Lock getLock() {
        return lock;
    }

    private int code;
    private Lock lock = new ReentrantLock();

    public Chopstick(int code) {
        this.code = code;
    }

    public boolean isToken() {
        return isToken;
    }

    public void setToken(boolean token) {
        isToken = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void takeChop(Philosophies philosophy) throws InterruptedException {
        if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            //拿不到这把锁就代表这根筷子被人用了
            System.out.println(philosophy.getName() + "尝试拿到" + code + "号筷子，但是失败了");
        } else {
            System.out.println(philosophy.getName() + "拿到" + code + "号筷子");
            if (philosophy.getLeft() == null) {
                philosophy.setLeft(this);
            } else if (philosophy.getRight() == null) {
                philosophy.setRight(this);
            }
            isToken = true;
        }
    }

    public void putChop(Philosophies philosophy) {
        if (Thread.holdsLock(lock)) {
            //说明持有该锁
            lock.unlock();
            isToken = false;
            System.out.println(philosophy.getName() + "放下了" + code + "号筷子");
        } else {
            System.out.println(philosophy.getName() + "未持有" + code + "号筷子");
        }
    }
}
