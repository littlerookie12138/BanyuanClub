package timed;

import java.util.Random;

public class Philosophy extends Thread {

    private static final Random rand = new Random();

    protected String name;

    public Philosophy(String name, Chopsticks left, Chopsticks right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    protected Chopsticks left;
    protected Chopsticks right;


    // @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (rand.nextBoolean()) {
                    eat();
                } else {
                    think();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                if (left.isToken()) {
                    left.put(this);
                } else if (right.isToken()) {
                    right.put(this);
                }
            }
        }

    }

    protected void think() {
        System.out.println(getName() + "准备思考");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + "思考完成");
    }

    protected void eat() throws InterruptedException {
        System.out.println(getName() + "准备吃饭");
        if (!this.left.take(this)) {
            System.out.println(getName() + "饿肚子");
            return;
        }
        Thread.sleep(500);
        if (!this.right.take(this)) {
            this.left.put(this);
            System.out.println(getName() + "饿肚子");
            return;
        }
        Thread.sleep(500);
        System.out.println(getName() + "吃饭完成");
        this.right.put(this);
        this.left.put(this);
    }



}
