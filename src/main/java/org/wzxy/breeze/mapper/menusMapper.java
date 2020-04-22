package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.Select;
import org.wzxy.breeze.model.po.menu;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface menusMapper {
    @Select("SELECT * FROM menu WHERE menuId IN " +
            "(SELECT menuId FROM role_menu WHERE roleId=#{roleId})")
    List<menu> findMenuByRoleId(int roleId);
}
