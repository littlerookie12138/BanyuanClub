import com.alibaba.fastjson.JSONObject;
import com.banyuan.jdbc.JdbcUtil;

import java.util.*;
import java.sql.SQLException;
import java.util.Map;

public class StudentService {

    public Student getStudentById(int sno) throws SQLException {
        // SQL语句
        String sql = "select sno, sname, ssex from Student where sno = ?";
        Map<String, Object> stringObjectMap = JdbcUtil.queryOne(sql, sno);

        Student student = JSONObject.parseObject(JSONObject.toJSONBytes(stringObjectMap), Student.class);

        return student;
    }

    public List<Student> getStudentList() throws SQLException {
        // SQL语句
        String sql = "select sno, sname, ssex from Student";
        List<Map<String, Object>> maps = JdbcUtil.queryAll(sql);
        List<Student> students = JSONObject.parseArray(JSONObject.toJSONString(maps), Student.class);

        return students;
    }

    // 修改学生信息
    public void updateStudent(Student student) throws SQLException {
        // 根据学生序号获取学生信息
        Student s = getStudentById(student.getSno());
        if (s != null) {
            s.setSname(student.getSname());
            s.setSsex(student.getSsex());
        }

        String sql = "update Student set sname = ?, ssex = ? where sno = ?";
        int affectLines = JdbcUtil.update(sql, s.getSname(), s.getSsex(), s.getSno());

        System.out.println("共有 " + affectLines + " 行受到影响.");
    }

    // 新增学生信息
    public void insertStudent(Student student) throws SQLException {
        // SQL语句
        String sql = "insert into Student(sname, ssex) values(?, ?)";

        int affectLines = JdbcUtil.update(sql, student.getSname(), student.getSsex());
        System.out.println("共有 " + affectLines + " 行受到影响.");
    }

    // 删除学生信息
    public void deleteStudent(Student student) throws SQLException {
        // SQL语句
        String sql = "delete from Student where sno = ?";

        int affectLines = JdbcUtil.update(sql, student.getSno());
        System.out.println("共有 " + affectLines + " 行受到影响.");
    }
}
