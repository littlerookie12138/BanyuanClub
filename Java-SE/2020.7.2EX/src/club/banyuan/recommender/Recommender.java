package club.banyuan.recommender;

import java.util.LinkedList;
import java.util.List;

public interface Recommender {
    void addLikes(String name, String likes);

    boolean bothLikes(String name, String like1, String like2);

    LinkedList<String> recommendByPerson(String name);

    List<String> recommendByProject(String like);

    List<String> mayLike(String name);

    List<String> mayLikeByProject(String project);
}
