public class Student {
    private int sno;
    private String sname;
    private String ssex;

    public Student(int sno, String sname, String ssex) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
    }

    public Student(String sname, String ssex) {
        this.sname = sname;
        this.ssex = ssex;
    }

    public Student() {
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                '}';
    }
}
