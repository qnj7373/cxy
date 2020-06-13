package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.wzxy.breeze.model.po.Maintenance;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface maintenanceMapper {


    @Insert("INSERT INTO maintenance (mainId,labId,personId,equId,equName,equtype,description,reportTime,personName)  " +
            "VALUES(#{mainId},#{labId},#{personId},#{equId},#{equName},#{equtype},#{description},#{reportTime},#{personName})")
    public boolean addMaintenance(Maintenance mainten);

    @Delete("DELETE FROM maintenance WHERE mainId = #{mainId}")
    public boolean deleteMaintenance(Maintenance mainten);

    @Select("SELECT * FROM maintenance WHERE mainId = #{mainId}")
    public Maintenance queryMaintenById(int mainId);


    @Select("SELECT IFNULL((SELECT 1 from maintenance WHERE mainId = #{mainId} limit 1),0)")
    public  int  isExist(int mainId);

    @Select("SELECT * FROM maintenance WHERE labId = #{labId}")
    public List<Maintenance> queryMaintenByLabId(int labId);

    @Select("SELECT * FROM maintenance WHERE personId = #{personId}")
    public List<Maintenance> queryMaintenBypersonId(int personId);

    @Update("UPDATE  maintenance SET mainId= #{mainId} , labId = #{labId}, personId = #{personId}" +
            " , equId = #{equId} , equName = #{equName}, equtype = #{equtype} " +
            ", description = #{description} , reportTime = #{reportTime}, personName = #{personName}" +
            " WHERE mainId = #{mainId}")
    public boolean updateMainten(Maintenance mainten);

    @Select("SELECT COUNT(*) FROM maintenance")
    public int getTotalCount();

    @Select("SELECT * FROM maintenance")
    public List<Maintenance> getAllMaintenance();

    @Select("SELECT * FROM maintenance LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Maintenance> getMaintenanceByPage(int nowPage, int pageSize);


}
