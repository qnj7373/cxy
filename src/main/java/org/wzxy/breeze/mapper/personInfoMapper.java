package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.PersonInfo;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface personInfoMapper {

    @Insert("INSERT INTO personinfo (personId,studentId,password,personName,major,depId,classId,grade,tel,email,photo,labId,hirSta)  " +
            "VALUES(#{personId},#{studentId},#{password},#{personName},#{major},#{depId}" +
            ",#{classId},#{grade},#{tel},#{email},#{photo},#{labId},#{hirSta})")
    public boolean addPersonInfo(PersonInfo Info);

    @Delete("DELETE  FROM personinfo WHERE personId = #{personId}")
    public boolean deletePersonInfoById(PersonInfo Info);

    @Select("SELECT * FROM personinfo WHERE personId = #{personId}")
    public PersonInfo queryPersonInfoById(int personId);


    @Select("SELECT IFNULL((SELECT 1 from personinfo WHERE personId = #{personId} limit 1),0)")
    public  int  isExist(int personId);

    @Select("SELECT * FROM personinfo WHERE depId = #{depId}")
    public List<PersonInfo> queryPersonInfosByDepId(int depId) ;

    @Select("SELECT * FROM personinfo WHERE labId = #{labId} AND hirSta = #{hirSta}")
    public List<PersonInfo> queryPersonByplabIdAndSta(@Param("labId") int labId,@Param("hirSta") String hirSta);

    @Select("SELECT * FROM personinfo WHERE StudentId = #{StudentId}")
    public PersonInfo queryPersonInfoByStudentId(int StudentId) ;

    @Update("UPDATE  personinfo SET personId= #{personId} , studentId = #{studentId} " +
            ", password = #{password}, personName = #{personName} , major = #{major}" +
            ", depId = #{depId}, classId = #{classId}, grade = #{grade}, tel = #{tel}" +
            ", email = #{email}, photo = #{photo}, labId = #{labId}, hirSta = #{hirSta}" +
            " WHERE personId = #{personId}")
    public boolean updatePersonInfo(PersonInfo Info);

    @Select("SELECT COUNT(*) FROM personinfo")
    public int getTotalCount();

    @Select("SELECT * FROM personinfo")
    public List<PersonInfo> getAllPersonInfo();

    @Select("SELECT * FROM personinfo LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<PersonInfo> getPersonInfoByPage(int nowPage, int pageSize);


}
