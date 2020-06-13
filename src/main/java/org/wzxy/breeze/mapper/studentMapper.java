package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.wzxy.breeze.model.po.Student;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface studentMapper {

    @Insert("INSERT INTO student (studentId,studentName,classId)  VALUES(#{studentId},#{studentName},#{classId})")
    public boolean addStudent(Student student);

    @Delete("DELETE  FROM student WHERE studentId = #{studentId}")
    public boolean deleteStudentById(Student student);

    @Select("SELECT * FROM student WHERE studentId = #{id}")
    public Student queryStudentById(int id);


    @Select("SELECT IFNULL((SELECT 1 from student WHERE studentId = #{id} limit 1),0)")
    public  int  isExist(int id);

    @Update("UPDATE  student SET studentId= #{studentId} , studentName = #{studentName} , classId = #{classId} " +
            "WHERE studentId = #{studentId}")
    public boolean updateStudent(Student student);

    @Select("SELECT COUNT(*) FROM student")
    public int getTotalCount();


    @Select("SELECT * FROM student WHERE studentId = #{studentId}")
    public List<Student> getStudentsByclassId(int classId);

    @Select("SELECT * FROM student LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Student> getStudentsByPage(int nowPage, int pageSize);




}
