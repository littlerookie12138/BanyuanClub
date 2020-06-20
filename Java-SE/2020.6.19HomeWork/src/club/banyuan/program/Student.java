package club.banyuan.program;

public class Student {
    protected String name;
    protected String level;

    public Student(String name) {
        this.name = name;
    }

    public Student() {

    }

    public final String getName() {
        return name;
    }

    protected final void setName(String name) {
        this.name = name;
    }

    protected final String getLevel() {
        return level;
    }

    protected final void setLevel(String level) {
        this.level = level;
    }
}
