package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wzxy.breeze.model.po.profiles;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface profilesMapper {

    @Select("select * from profiles where id = ${id}")
    public profiles findFilesById(@Param("id") int id);


    @Select("SELECT IFNULL((SELECT 1 from profiles where id = ${id} limit 1),0)")
    public  int  isExist(@Param("id") int id);

}
