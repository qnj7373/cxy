package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.role;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface rolesMapper {
    @Select("SELECT * FROM role WHERE roleId IN (SELECT roleId FROM user_role WHERE userId=#{userId})")
    @Results({
            @Result(id=true,column="roleId",property="roleId"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleGroup",property="roleGroup"),
            @Result(column = "roleId",property = "menus",
                    one = @One(select = "org.wzxy.breeze.mapper.menusMapper.findMenuByRoleId",fetchType = FetchType.LAZY)
            )
    })
    List<role> findRoleByUserId(int userId);


    @Select("SELECT * FROM role WHERE roleId IN (SELECT roleId FROM user_role WHERE uid=#{uid})")
    @Results({
            @Result(id=true,column="roleId",property="roleId"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleGroup",property="roleGroup"),
            @Result(column = "roleId",property = "menus",
                    one = @One(select = "org.wzxy.breeze.mapper.menusMapper.findMenuByRoleId",fetchType = FetchType.LAZY)
            )
    })
    List<role> findRoleByUid(int uid);
}
