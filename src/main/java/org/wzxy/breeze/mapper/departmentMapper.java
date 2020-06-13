package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.wzxy.breeze.model.po.Department;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface departmentMapper {

    @Insert("INSERT INTO department (depId,depName)  VALUES(#{depId},#{depName})")
    public boolean addDep(Department Dep);

    @Delete("DELETE  FROM department WHERE depId = #{depId}")
    public boolean deleteDepById(Department Dep);

    @Select("SELECT * FROM department WHERE depId = #{depId}")
    public Department queryDepById(int depId);

    @Select("SELECT IFNULL((SELECT 1 from department WHERE depId = #{depId} limit 1),0)")
    public  int  isExist(int depId);

    @Update("UPDATE  department SET depId= #{depId} , depName = #{depName} WHERE depId = #{depId}")
    public boolean updateDep(Department Dep);

    @Select("SELECT COUNT(*) FROM department")
    public int getTotalCount();

    @Select("SELECT * FROM department")
    public List<Department> getAllDeps();

    @Select("SELECT * FROM department LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Department> getDepsByPage(int nowPage, int pageSize);


}
