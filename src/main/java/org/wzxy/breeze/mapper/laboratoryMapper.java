package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.Laboratory;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface laboratoryMapper {

    @Insert("INSERT INTO laboratory (labId,labName,depId,techId) " +
            " VALUES(#{labId},#{labName},#{depId},#{techId})")
    public boolean addLaboratory(Laboratory lab);

    @Delete("DELETE  FROM laboratory WHERE labId = #{labId}")
    public boolean deleteLaboratoryById(Laboratory lab);

    @Select("SELECT * FROM laboratory WHERE labId = #{labId}")
    @Results({
            @Result(id=true,column="labId",property="labId"),
            @Result(column="labName",property="labName"),
            @Result(column="depId",property="depId"),
            @Result(column="techId",property="techId"),
            @Result(column = "labId",property = "maintenances",
                    many = @Many(select = "org.wzxy.breeze.mapper.maintenanceMapper.queryMaintenByLabId",fetchType = FetchType.LAZY)
            ),
            @Result(column = "labId",property = "workRecords",
                    many = @Many(select = "org.wzxy.breeze.mapper.workRecordMapper.queryWorkRecordBylabId",fetchType = FetchType.LAZY)
            ),
            @Result(column = "labId",property = "plans",
                    many = @Many(select = "org.wzxy.breeze.mapper.planMapper.queryPlanByLabId",fetchType = FetchType.LAZY)
            )

    })
    public Laboratory queryLaboratoryById(int labId);


    @Select("SELECT IFNULL((SELECT 1 from laboratory WHERE labId = #{labId} limit 1),0)")
    public  int  isExist(int labId);

    @Select("SELECT * FROM laboratory WHERE labName LIKE '%${labName}%'  AND depId=${depId}")
    public List<Laboratory> queryLabBylabNameAndDepId(@Param("labName")String labName, @Param("depId")int depId);

    @Select("SELECT * FROM laboratory WHERE techId = #{techId}")
    public List<Laboratory> queryLaboratorysByTechId(int techId) ;

    @Select("SELECT * FROM laboratory WHERE depId = #{depId}")
    public List<Laboratory> queryLaboratorysByDepId(int depId) ;

    @Update("UPDATE  laboratory SET labId= #{labId} , labName = #{labName} ," +
            " depId = #{depId},techId = #{techId}" +
            " WHERE labId= #{labId}")
    public boolean updateLaboratory(Laboratory lab);

    @Select("SELECT COUNT(*) FROM laboratory")
    public int getTotalCount();

    @Select("SELECT * FROM laboratory")
    public List<Laboratory> getAllLaboratory();

    @Select("SELECT * FROM laboratory LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Laboratory> getLaboratoryByPage(int nowPage, int pageSize);


}
