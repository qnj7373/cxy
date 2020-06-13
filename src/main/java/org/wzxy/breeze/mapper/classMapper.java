package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.wzxy.breeze.model.po.Class;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface classMapper {

    @Insert("INSERT INTO class (classId,className,depId)  VALUES(#{classId},#{className},#{depId})")
    public boolean addClass(Class cla);

    @Delete("DELETE  FROM class WHERE classId = #{classId}")
    public boolean deleteClassById(Class cla);

    @Select("SELECT * FROM class WHERE classId = #{classId}")
    public Class queryClassById(int classId);

    @Select("SELECT IFNULL((SELECT 1 from class WHERE classId = #{classId} limit 1),0)")
    public  int  isExist(int classId);

    @Update("UPDATE  users SET classId = #{classId} , className = #{className} WHERE depId = #{depId}")
    public boolean updateClass(Class cla);

    @Select("SELECT COUNT(*) FROM class")
    public int getTotalCount();

    @Select("SELECT * FROM class WHERE depId = #{depId}")
    public List<Class> getClassBydepId(int depId);

    @Select("SELECT * FROM class")
    public List<Class> getAllClass();

    @Select("SELECT * FROM class LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Class> getClassByPage(int nowPage, int pageSize);
}
