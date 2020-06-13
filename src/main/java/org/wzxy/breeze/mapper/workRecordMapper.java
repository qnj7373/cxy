package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.WorkRecord;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface workRecordMapper {


    @Insert("INSERT INTO workRecord (recordId,weeklyTimes,labId,date,week,personId,personName,Health,equipment,sysTime)  " +
            " VALUES(#{recordId},#{weeklyTimes},#{labId},#{date},#{week},#{personId},#{personName},#{Health},#{equipment},#{sysTime})")
    public boolean addWorkRecord(WorkRecord Wrecord);

    @Delete("DELETE FROM workRecord WHERE recordId = #{recordId}")
    public boolean deleteWorkRecord(WorkRecord Wrecord);

    @Select("SELECT * FROM workRecord WHERE recordId = #{recordId}")
    public WorkRecord queryWorkRecordById(int recordId);

    @Select("SELECT IFNULL((SELECT 1 fromworkRecord WHERE recordId = #{recordId} limit 1),0)")
    public  int  isExist(int recordId);

    @Select("SELECT * FROM workRecord WHERE labId = #{labId}")
    public List<WorkRecord> queryWorkRecordBylabId(int labId);

    @Select("SELECT * FROM workRecord WHERE labId = #{labId} AND date = #{date}")
    public List<WorkRecord> queryWorkRecordByDateAndLabId(@Param("labId") int labId, @Param("date") String date);

    @Select("SELECT * FROM workRecord WHERE  personId = #{ personId}")
    public List<WorkRecord> queryWorkRecordBypersonId(int personId);

    @Update("UPDATE  workRecord SET recordId= #{recordId} , weeklyTimes = #{weeklyTimes} , labId = #{labId} " +
            ", date = #{date}, week = #{week}, personId = #{personId} " +
            ", personName = #{personName}, Health = #{Health}, equipment = #{equipment} " +
            " , sysTime = #{sysTime} " +
            "  WHERE recordId = #{recordId}")
    public boolean updateWorkRecord(WorkRecord Wrecord);

    @Select("SELECT COUNT(*) FROM workRecord")
    public int getTotalCount();

    @Select("SELECT * FROM workRecord")
    public List<WorkRecord> getAllWorkRecord();

    @Select("SELECT * FROM workRecord LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<WorkRecord> getWorkRecordByPage(int nowPage, int pageSize);


}
