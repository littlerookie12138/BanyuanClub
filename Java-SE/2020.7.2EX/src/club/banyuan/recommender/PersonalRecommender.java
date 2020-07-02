package club.banyuan.recommender;

import java.util.*;
import java.util.function.BiConsumer;

public class PersonalRecommender implements Recommender{
    private Map<String, LinkedList<String>> likes = new HashMap<String, LinkedList<String>>();

    public void addLikes(String name, String likes) {
        if (this.likes.get(name) == null) {
            //喜好列表中不存在此人
            throw new UnknownPersonException("没有找到此人!");
        }
        this.likes.get(name).add(likes);
    }

    public Map<String, LinkedList<String>> getLikes() {
        return likes;
    }

    @Override
    public boolean bothLikes(String name, String like1, String like2) {
        if (this.likes.get(name) == null) {
            //喜好列表中不存在此人
            throw new UnknownPersonException("没有找到此人!");
        }
        for (String s : this.likes.get(name)) {
            int likeFirst = 0;
            int likeSecond = 0;
            if (s.equals(like1)) {
                likeFirst = 1;
            }
            if (s.equals(like2)) {
                likeSecond = 1;
            }

            if (likeFirst == likeSecond) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LinkedList<String> recommendByPerson(String name) {
        return likes.get(name);
    }

    @Override
    public String toString() {
        return "PersonalRecommender{" +
                "likes=" + likes +
                '}';
    }

    @Override
    public List<String> recommendByProject(String like) {
        List<String> finalList= new ArrayList<>();
        likes.forEach((key, value) -> {
            if (value.contains(like)) {
                finalList.addAll(likes.get(key));
            }
        });
        return finalList;
    }

    @Override
    public List<String> mayLike(String name) {
        List<String> userLike = likes.get(name);
        List<String> finalList= new ArrayList<>();
        likes.forEach((key, value) -> {
            for (int i = 0; i < userLike.size(); i++) {
                if (value.contains(userLike.get(i))) {
                    finalList.addAll(likes.get(key));
                }
            }
        });
        finalList.removeAll(userLike);
        return finalList;
    }

    @Override
    public List<String> mayLikeByProject(String project) {
        List<String> finalList= new ArrayList<>();
        likes.forEach((key, value) -> {
                if (value.contains(project)) {
                    finalList.addAll(likes.get(key));
                }
        });
        List<String> removeList = new ArrayList<>();
        removeList.add(project);
        finalList.removeAll(removeList);
        return finalList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalRecommender that = (PersonalRecommender) o;
        return Objects.equals(likes, that.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likes);
    }
}
