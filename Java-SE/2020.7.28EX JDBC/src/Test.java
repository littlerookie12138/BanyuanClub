import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        StudentService studentService = new StudentService();
        System.out.println(studentService.getStudentById(102));

        System.out.println(studentService.getStudentList());
        studentService.updateStudent(new Student(102,"张亮", "男"));
        System.out.println(studentService.getStudentList());

        // 添加一个学生
        studentService.insertStudent(new Student("王二麻子", "男"));
        System.out.println(studentService.getStudentList());

        // 删除一个学生
        studentService.deleteStudent(new Student(102, "张亮", "男"));
        System.out.println(studentService.getStudentList());
    }
}
