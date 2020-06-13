package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.Enlist;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface enlistMapper {

    @Insert("INSERT INTO enlist (enlistId,postName,personName,skill,personId,planId,appDate,signSta) " +
            " VALUES(#{enlistId},#{postName},#{personName},#{skill},#{personId},#{planId},#{appDate},#{signSta})")
    public boolean addEnlist(Enlist enlist);

    @Delete("DELETE FROM enlist WHERE enlistId = #{enlistId}")
    public boolean deleteEnlistById(Enlist enlist);

    @Select("SELECT * FROM enlist WHERE enlistId = #{enlistId}")
    public Enlist queryEnlistById(int enlistId);

    @Select("SELECT IFNULL((SELECT 1 from enlist WHERE enlistId = #{enlistId} limit 1),0)")
    public  int  isExist(int enlistId);

    @Select("SELECT * FROM enlist WHERE personId = #{personId}")
    public Enlist queryEnlistBypersonId(int personId);

    @Select("SELECT * FROM enlist WHERE planId = #{planId}")
    public List<Enlist> queryEnlistByplanId(int planId);

    @Select("SELECT * FROM enlist WHERE planId = #{planId} AND  signSta = #{signSta}")
    public List<Enlist> queryEnlistByplanIdAndSta(@Param("planId") int planId, @Param("signSta") String signSta);


    @Select("SELECT * FROM enlist WHERE signSta = #{signSta}")
    public List<Enlist> queryEnlistBysignSta(String signSta);

    @Select("SELECT COUNT(*) FROM enlist WHERE planId = #{planId}")
    public int countSign(int planId);

    @Update("UPDATE  enlist SET enlistId= #{enlistId} , postName = #{postName} ," +
            "personName  = #{personName} ,skill= #{skill} ,personId= #{personId},planId= #{planId}" +
            ",appDate= #{appDate},signSta= #{signSta}" +
            "WHERE enlistId = #{enlistId}")
    public boolean updateEnlist(Enlist enlist);

    @Select("SELECT COUNT(*) FROM enlist")
    public int getTotalCount();

    @Select("SELECT * FROM enlist")
    public List<Enlist> getAllEnlist();

    @Select("SELECT * FROM enlist LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<Enlist> getEnlistByPage(int nowPage, int pageSize);

}
