package club.banyuan.program;

public class Test extends Student{
    public static void main(String[] args) {
        Pupil p1 = new Pupil("张三");
        p1.setName("张武");
        p1.setLevel("六年级");
        System.out.println(p1.getName());
        System.out.println(p1.getLevel());

        JuniorHighSchool j1 = new JuniorHighSchool("王五");
        j1.setName("王二");
        j1.setLevel("初二");
        System.out.println(j1.getName());
        System.out.println(j1.getLevel());

        HighSchool h1 = new HighSchool("赵四");
        h1.setName("赵六");
        h1.setLevel("高一");
        System.out.println(h1.getName());
        System.out.println(h1.getLevel());

//        System.out.println(Student);
    }
}
