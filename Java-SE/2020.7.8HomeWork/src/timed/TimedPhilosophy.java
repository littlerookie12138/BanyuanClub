package timed;

public class TimedPhilosophy extends Philosophy {
    private int countEat;
    private int countThink;
    private int countHungry;

    public TimedPhilosophy(String name, Chopsticks left, Chopsticks right) {
        super(name, left, right);
    }

    @Override
    protected void think() {
        System.out.println(getName() + "准备思考");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(getName() + "思考完成");
        countThink++;
    }

    @Override
    protected void eat() throws InterruptedException {
        System.out.println(getName() + "准备吃饭");
        if (!this.left.take(this)) {
            System.out.println(getName() +"饿肚子");
            countHungry++;
            return;
        }
        Thread.sleep(500);
        if (!this.right.take(this)) {
            this.left.put(this);
            System.out.println(getName() + "饿肚子");
            countHungry++;
            return;
        }
        Thread.sleep(500);
        System.out.println(getName() + "吃饭完成");
        countEat++;
        this.right.put(this);
        this.left.put(this);
    }

    public int getCountEat() {
        return countEat;
    }

    public int getCountThink() {
        return countThink;
    }

    public int getCountHungry() {
        return countHungry;
    }

    @Override
    public String toString() {
        return this.getName() + "{" +
                "countEat=" + countEat +
                ", countThink=" + countThink +
                ", countHungry=" + countHungry +
                '}';
    }
}
