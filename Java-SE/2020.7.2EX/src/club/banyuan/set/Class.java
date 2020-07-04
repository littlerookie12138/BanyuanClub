package club.banyuan.set;

import java.util.*;

public class Class {
    public static void print(Set<Student> set) {
        for (Student student : set) {
            System.out.println(student);
        }
    }

    //升序
    public static List<Student> ascendingOrder(Set<Student> set, boolean isAscendOrDes) {
        List<Student> list = new ArrayList<>(set);
        Collections.sort(list, (o1, o2) -> {
            if (isAscendOrDes) {
                return o1.getId() - o2.getId();
            } else {
                return o2.getAge() - o1.getAge();
            }
        });
        return list;
    }



    public static void main(String[] args) {
        Set<Student> classRoster = new HashSet<>();
        List<Student> list = new ArrayList<>();
        classRoster.add(new Student(1, "张三", 12));
        classRoster.add(new Student(2, "李顽强", 58));
        classRoster.add(new Student(3, "戴宇翔", 16));
        classRoster.add(new Student(4, "石欢程", 18));

        list.add(new Student(5, "王二", 14));
        list.add(new Student(6, "王三", 14));
        list.add(new Student(7, "王四", 14));

        classRoster.addAll(list);
        print(classRoster);
        System.out.println(new Student(6, "王三", 14).equals(new Student(7, "王四", 14)));
        //升序排列
        System.out.println(ascendingOrder(classRoster, true));
        //降序排列
//        ListIterator<Student> listIterator = ascendingOrder(classRoster).listIterator();
//        for (listIterator = ascendingOrder(classRoster).listIterator(); listIterator.hasNext(); ) {
//            listIterator.next();
//        }
//        for (; listIterator.hasPrevious();) {
//            System.out.print(listIterator.previous() + ",");
//        }
        Set<Student> descendSet = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (!o1.equals(o2)) {
                    return o2.getAge() - o1.getAge();
                }
                return 0;
            }
        });
        descendSet.addAll(classRoster);
        System.out.println(descendSet);
    }

    private static int compare(Student o1, Student o2) {
        return o2.getId() - o1.getId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
