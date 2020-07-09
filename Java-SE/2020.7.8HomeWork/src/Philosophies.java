import java.util.concurrent.TimeUnit;

public class Philosophies extends Thread {
    //左右手拿起的筷子
    private Chopstick left;
    private Chopstick right;
    private String name;
    private int code;
    private boolean isReadyToEat;

    public Philosophies(String name, Chopstick left, Chopstick right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

//    public String getName() {
//        return name;
//    }

    public Chopstick getLeft() {
        return left;
    }

    public Chopstick getRight() {
        return right;
    }

    public void setLeft(Chopstick left) {
        this.left = left;
    }

    public void setRight(Chopstick right) {
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            try {
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //哲学家拿起筷子
    public void pickChopUp(Chopstick left, Chopstick right) {
        try {
            if (left.getLock().tryLock(500, TimeUnit.MILLISECONDS) && right.getLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                //拿到了两根筷子准备吃饭
                isReadyToEat = true;
                System.out.println(this.getName() + "准备好吃饭了，拿到了" + left.getCode() + "," + right.getCode() + "两根筷子");
                return;
            } else if (!left.getLock().tryLock(500, TimeUnit.MILLISECONDS) || !right.getLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                //有某一根筷子没有拿到，放下所有的筷子
                this.left = null;
                this.right = null;
                System.out.println(this.getName() + "饿肚子了");
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //放下筷子
    public void putChopDown() {
        if (isReadyToEat) {
            left.getLock().unlock();
            right.getLock().unlock();
            isReadyToEat = false;
            System.out.println(this.getName() + "放下了" + left.getCode() + "及" + right.getCode() + "两根筷子");
            return;
        } else {
            System.out.println("没有拿到筷子怎么放下");
        }
    }

    //吃饭
    private void eat() throws InterruptedException {
        if (this.right == null || this.left == null) {
            //还没拿到两根筷子
            System.out.println("还没拿到两根筷子怎么吃饭？");
            return;
        } else {
            Thread.sleep(500);
            System.out.println(this.getName() + "吃完饭了");
            isReadyToEat = false;
            this.left.putChop(this);
            this.right.putChop(this);
            this.putChopDown();
        }
    }

//    @Override
//    public String call() throws Exception {
//        return null;
//    }
}
