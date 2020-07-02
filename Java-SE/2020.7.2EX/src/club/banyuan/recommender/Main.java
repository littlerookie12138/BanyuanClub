package club.banyuan.recommender;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        PersonalRecommender personalRecommender = new PersonalRecommender();
        LinkedList<String> aLikes = new LinkedList<>();
        LinkedList<String> bLikes = new LinkedList<>();
        LinkedList<String> cLikes = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            aLikes.add("A" + i);
        }
        aLikes.add("3");

        bLikes.add("a");
        bLikes.add("1");
        bLikes.add("c");

        cLikes.add("3");
        personalRecommender.getLikes().put("A", aLikes);
        personalRecommender.getLikes().put("B", bLikes);
        personalRecommender.getLikes().put("C", cLikes);

        personalRecommender.getLikes().forEach((key, value) -> System.out.println(key + "" + value));

        personalRecommender.addLikes("A", "TestAaddLikes");
        try {
            personalRecommender.addLikes("D", "TestAaddLikes");
        } catch (UnknownPersonException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(personalRecommender.recommendByPerson("A"));
        System.out.println(personalRecommender.bothLikes("A", "A0", "5"));
        System.out.println(personalRecommender.recommendByProject("3"));

        System.out.println(personalRecommender.mayLike("C"));
    }
}
