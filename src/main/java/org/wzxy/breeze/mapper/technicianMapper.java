package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.Technician;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface technicianMapper {


    @Insert("INSERT INTO technician (techId,techName,depId,workSta) " +
            "  VALUES(#{techId},#{techName},#{depId},#{workSta})")
    public boolean addTechnician(Technician technician);

    @Delete("DELETE  FROM technician WHERE techId = #{techId}")
    public boolean deleteTechnicianById(Technician technician);


    @Select("SELECT IFNULL((SELECT 1 from technician WHERE techId = #{techId} limit 1),0)")
    public  int  isExist(int techId);

    @Select("SELECT * FROM technician WHERE techId = #{techId}")
    public Technician queryTechnicianById(int techId);

    @Select("SELECT * FROM technician WHERE workSta = #{workSta} AND depId = #{depId}")
    public List<Technician> queryTechnicianByworkSta(@Param("workSta")String workSta, @Param("depId")int depId);

    @Update("UPDATE  technician SET techId= #{techId} , techName = #{techName} , workSta = #{workSta}, depId = #{depId} " +
            " WHERE techId = #{techId}")
    public boolean updateTechnician(Technician technician);

    @Select("SELECT COUNT(*) FROM technician")
    public int getTotalCount();

    @Select("SELECT * FROM technician")
    public List<Technician> getAllTechnician();

    @Select("SELECT * FROM technician LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Technician> getTechnicianByPage(int nowPage, int pageSize);


}
