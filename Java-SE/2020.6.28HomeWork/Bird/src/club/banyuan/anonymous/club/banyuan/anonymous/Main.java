package club.banyuan.anonymous;

public class Main {

  public static void show(Bird bird) {
    System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
  }

  public static void main(String[] args) {

    show(new Bird() {
      //TODO
      public String getName() {
        return "燕子";
      }
      public int fly() {
        return 1000;
      }
    });
  }
}