package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.wzxy.breeze.model.po.Plan;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface planMapper {


    @Insert("INSERT INTO plan (planId,planName,requirement,techName,planSta,number,depId,depName,labId,labName,techId)  " +
            " VALUES(#{planId},#{planName},#{requirement},#{techName},#{planSta},#{number},#{depId},#{depName},#{labId},#{labName},#{techId})")
    public boolean addPlan(Plan plan);

    @Delete("DELETE  FROM plan WHERE planId = #{planId}")
    public boolean deletePlanById(Plan plan);

    @Select("SELECT * FROM plan  WHERE planId = #{planId}")
    public Plan queryPlanById(int planId);



    @Select("SELECT IFNULL((SELECT 1 from plan  WHERE planId = #{planId} limit 1),0)")
    public  int  isExist(int planId);

    @Select("SELECT * FROM plan WHERE depId = #{depId}")
    public List<Plan> queryPlanBydepId(int depId);

    @Select("SELECT * FROM plan WHERE techId = #{techId}")
    public List<Plan> queryPlanByTechId(int techId);

    @Select("SELECT * FROM plan WHERE labId = #{labId}")
    public List<Plan> queryPlanByLabId(int labId);

    @Select("SELECT * FROM plan WHERE planSta = #{planSta}")
    public List<Plan> queryPlanByPlanSta(String planSta);

    @Update("UPDATE  plan SET planId= #{planId} , planName = #{planName} " +
            ", requirement = #{requirement}, techName = #{techName}, planSta = #{planSta}" +
            ", number = #{number}, depId = #{depId}, depName = #{depName}" +
            ", labId = #{labId}, labName = #{labName}, techId = #{techId} " +
            " WHERE planId = #{planId}")
    public boolean updatePlan(Plan plan);

    @Select("SELECT COUNT(*) FROM plan")
    public int getTotalCount();

    @Select("SELECT * FROM plan")
    public List<Plan> getAllPlan();

    @Select("SELECT * FROM plan LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Plan> getPlanByPage(int nowPage, int pageSize);


}
